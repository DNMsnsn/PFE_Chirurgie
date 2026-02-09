package Controller; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import genDoc.ArretTravailGenerator;
import Model.ArretTravail;
import Model.ArretTravailDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/DownloadArretTravailPDF")
public class DownloadArretTravailPDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
                return;
            }

            int id = Integer.parseInt(idStr);

            ArretTravailDao arrDao = new ArretTravailDao(GetConnected.getConnected());
            ArretTravail a = arrDao.getById(id); 
            if (a == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Arrêt introuvable");
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", 
                    "attachment; filename=arret_travail_" + id + ".pdf");

            Path temp = Files.createTempFile("arret_", ".pdf");

            String urlCertificat = "http://localhost:8080/revisionControle/ResultatArretTravail?id=" + id; 
            
            ArretTravailGenerator.genererCertificat(
                    temp.toString(),
                    "HOPITAL CENTRAL D'AZAZGA",
                    a.getNom_medecin(),
                    a.getPrenom_medecin().toUpperCase(),
                    a.getNom_patient(),
                    a.getPrenom_patient().toUpperCase(),
                    a.getDate_naiss_patient(),
                    a.getType(),
                    a.getDate_debut(),
                    a.getDate_fin(),
                    urlCertificat
            );
            
          /// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("DOWNLOAD_Arret_Travail");
			hs.setDetails("ID_Arret_Travail : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);

            Files.copy(temp, response.getOutputStream());
            response.getOutputStream().flush();
            Files.deleteIfExists(temp);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                               "Erreur lors de la génération du PDF");
        }
    }
}
