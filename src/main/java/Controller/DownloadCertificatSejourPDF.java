package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import genDoc.CertificatSejourGenerator;
import Model.CertificatSejour;
import Model.CertificatSejourDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@WebServlet("/DownloadCertificatSejourPDF")
public class DownloadCertificatSejourPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Récupérer l'ID du certificat depuis la requête
            String idStr = request.getParameter("id");
            if (idStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
                return;
            }
            int id = Integer.parseInt(idStr);

            // Récupérer le certificat depuis la base
            CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
            CertificatSejour c = dao.getById(id); // méthode DAO à créer
            if (c == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Certificat introuvable");
                return;
            }

            // Préparer le response pour téléchargement PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", 
                    "attachment; filename=certificat_sejour_" + id + ".pdf");

            // Créer un fichier temporaire et générer le PDF dedans
            Path temp = Files.createTempFile("certificat_sejour_", ".pdf");

            // URL dynamique pour le QR code (si nécessaire)
            String urlCertificat = "http://localhost:8080/revisionControle/ResultatCertificatSejour?id=" + id;

            CertificatSejourGenerator.genererCertificat(
                    temp.toString(),              
                    "HOPITAL CENTRAL D'AZAZGA",   
                    c.getNom_patient(),           
                    c.getPrenom_patient(),
                    LocalDate.parse(c.getDate_naiss_patient()),
                    LocalDate.parse(c.getDate_debut()),
                    LocalDate.parse(c.getDate_fin()),
                    urlCertificat
            );
            
          /// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("DOWNLOAD_Certificat_Sejour");
			hs.setDetails("ID_Certificat_Sejour : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);

            // Envoyer le PDF généré au client
            Files.copy(temp, response.getOutputStream());
            response.getOutputStream().flush();

            // 6️⃣ Supprimer le fichier temporaire
            Files.deleteIfExists(temp);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                               "Erreur lors de la génération du PDF");
        }
    }
}
