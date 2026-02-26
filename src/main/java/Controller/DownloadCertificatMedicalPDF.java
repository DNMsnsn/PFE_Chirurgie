package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import genDoc.CertificatMedicalGenerator;
import Model.CertificatMedical;
import Model.CertificatMedicalDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@WebServlet("/DownloadCertificatMedicalPDF")
public class DownloadCertificatMedicalPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Récupérer l'ID du patient depuis la requête
            String idStr = request.getParameter("id");
            if (idStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
                return;
            }
            int id = Integer.parseInt(idStr);

            // Récupérer les infos du patient depuis la base
            CertificatMedicalDao patientDao = new CertificatMedicalDao(GetConnected.getConnected());
            CertificatMedical p = patientDao.getById(id); 
            if (p == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient introuvable");
                return;
            }

            // Préparer la réponse pour téléchargement PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", 
                    "attachment; filename=certificat_medical_" + id + ".pdf");

            // Créer un fichier temporaire et générer le PDF dedans
            Path temp = Files.createTempFile("certificat_", ".pdf");

            String urlCertificat = "https://azazmed.net/ResultatCertificatMedical?id=" + id;

            CertificatMedicalGenerator.genererCertificat(
                    temp.toString(),                 
                    "HOPITAL CHAHIDS MAHMOUDI",      
                    p.getNom_medecin(),              
                    p.getPrenom_medecin().toUpperCase(), 
                    p.getNom_patient(),                       
                    p.getPrenom_patient().toUpperCase(),      
                    p.getDate_emission(),
                    LocalDate.parse(p.getDate_naiss_patient()),            
                    p.getStatut(),
                    urlCertificat
            );
            
          /// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("DOWNLOAD_Certificat_Medical");
			hs.setDetails("ID_Certificat_Medical : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);

            // Envoyer le PDF généré au client
            Files.copy(temp, response.getOutputStream());
            response.getOutputStream().flush();

            // Supprimer le fichier temporaire
            Files.deleteIfExists(temp);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                               "Erreur lors de la génération du PDF");
        }
    }
}
