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

@WebServlet("/AddCertificatSejour")
public class AddCertificatSejour extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String pfnm = request.getParameter("pfnm");
		String psnm = request.getParameter("psnm");
		
		try {
			CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
			
			int idPatient = dao.idPatient(pfnm, psnm);
			if(idPatient > 0) {
				
				CertificatSejour sej = new CertificatSejour();
				sej.setDate_debut(debut);
				sej.setDate_fin(fin);
				sej.setId_patient(idPatient);
				
				if(dao.create(sej)) {
					
					/// Historic ///
					HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
					Historique hs = new Historique();
					
					Object idObj = request.getSession().getAttribute("id");

					int idamsp = (idObj != null) ? (Integer) idObj : 0;
					
					hs.setType_histo("ADD_Certificat_Sejour");
					hs.setDetails("Date_debut: " + debut + " | Date_fin: " + fin);
					hs.setID_amsp(idamsp);
					hs.setID_patient(idPatient);
					
					h.create(hs);
					
					response.sendRedirect("ListCertificatSejour");
				} else {
					request.setAttribute("error", "Certificat de Sejour non Ajouter !");
					request.setAttribute("listSejour", dao.listSejour());
					request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatSejour.jsp").forward(request, response);
				}
				
			} else {
				request.setAttribute("error", "Patient Introuvable !");
				request.setAttribute("listSejour", dao.listSejour());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatSejour.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
