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

@WebServlet("/ResultatCertificatSejour")
public class ResultatCertificatSejour extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
			CertificatSejour sej = new CertificatSejour();
			
			/// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("CONSULTATION_Certificat_Sejour");
			hs.setDetails("ID_Certificat_Sejour : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);
			
			sej = dao.getById(id);
			
			request.setAttribute("id", id);
			request.setAttribute("nomPat", sej.getNom_patient());
			request.setAttribute("prePat", sej.getPrenom_patient());
			request.setAttribute("debut", sej.getDate_debut());
			request.setAttribute("fin", sej.getDate_fin());
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/CertificatSejourQr.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
