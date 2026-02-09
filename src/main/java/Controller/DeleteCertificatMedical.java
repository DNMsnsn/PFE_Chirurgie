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

@WebServlet("/DeleteCertificatMedical")
public class DeleteCertificatMedical extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		CertificatMedical cert = new CertificatMedical();
		
		try {
			CertificatMedicalDao dao = new CertificatMedicalDao(GetConnected.getConnected());
			
			CertificatMedical c = dao.find(id);
			
			cert.setId_certificat(id);
			
			if(dao.delete(cert)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Certificat_Medical");
				hs.setDetails("ID_Medecin : " + c.getId_medecin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(c.getId_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListCertificatMedical");
			} else {
				request.setAttribute("error", "Certificat Medical non Ajouter !");
				request.setAttribute("listCertificat", dao.listCertificat());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatMedical.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
