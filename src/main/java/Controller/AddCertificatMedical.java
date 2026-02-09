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

@WebServlet("/AddCertificatMedical")
public class AddCertificatMedical extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stat = request.getParameter("statut");
		String pfnm = request.getParameter("pfnm");
		String psnm = request.getParameter("psnm");
		String mfnm = request.getParameter("mfnm");
		String msnm = request.getParameter("msnm");
		
		CertificatMedical cert = new CertificatMedical();
		
		try {
			CertificatMedicalDao dao = new CertificatMedicalDao(GetConnected.getConnected());
			
			int idPatient = dao.idPatient(pfnm, psnm);
			int idMedecin = dao.idMedecin(mfnm, msnm);
			
			if(idPatient > 0 && idMedecin > 0) {
				
				cert.setStatut(stat);
				cert.setId_patient(idPatient);
				cert.setId_medecin(idMedecin);
				
				if(dao.create(cert)) {
					
					/// Historic ///
					HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
					Historique hs = new Historique();
					
					Object idObj = request.getSession().getAttribute("id");

					int idamsp = (idObj != null) ? (Integer) idObj : 0;
					
					hs.setType_histo("ADD_Certificat_Medical");
					hs.setDetails("ID_Medecin : " + idMedecin);
					hs.setID_amsp(idamsp);
					hs.setID_patient(idPatient);
					
					h.create(hs);
					
					response.sendRedirect("ListCertificatMedical");
				} else {
					request.setAttribute("error", "Certificat Medical non Ajouter !");
					request.setAttribute("listCertificat", dao.listCertificat());
					request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatMedical.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Patient ou Medecin Introuvable !");
				request.setAttribute("listCertificat", dao.listCertificat());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatMedical.jsp").forward(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
