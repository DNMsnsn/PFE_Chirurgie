package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CertificatMedical;
import Model.CertificatMedicalDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResultatCertificatMedical")
public class ResultatCertificatMedical extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			CertificatMedicalDao dao = new CertificatMedicalDao(GetConnected.getConnected());
			CertificatMedical cer = new CertificatMedical();
			
			/// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("CONSULTATION_Certificat_Medical");
			hs.setDetails("ID_Certificat_Medical : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);
			
			cer = dao.getById(id);
			
			request.setAttribute("id", id);
			request.setAttribute("nomPat", cer.getNom_patient());
			request.setAttribute("prePat", cer.getPrenom_patient());
			request.setAttribute("nomMed", cer.getNom_medecin());
			request.setAttribute("preMed", cer.getPrenom_medecin());
			request.setAttribute("stat", cer.getStatut());
			request.setAttribute("date", cer.getDate_emission());
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/CertificatMedicalQr.jsp").forward(request, response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
