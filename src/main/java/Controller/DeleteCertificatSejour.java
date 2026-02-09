package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CertificatSejour;
import Model.CertificatSejourDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCertificatSejour")
public class DeleteCertificatSejour extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		CertificatSejour sej = new CertificatSejour();
		sej.setId_titre(id);
		
		try {
			CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
			
			CertificatSejour c = dao.find(id);
			
			if(dao.delete(sej)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Certificat_Sejour");
				hs.setDetails("Date_debut: " + c.getDate_debut() + " | Date_fin: " + c.getDate_fin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(c.getId_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListCertificatSejour");
			} else {
				request.setAttribute("error", "Certificat de Sejour non Supprimer !");
				request.setAttribute("listSejour", dao.listSejour());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatSejour.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
