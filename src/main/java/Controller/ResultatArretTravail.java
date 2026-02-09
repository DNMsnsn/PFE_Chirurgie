package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.ArretTravail;
import Model.ArretTravailDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResultatArretTravail")
public class ResultatArretTravail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			ArretTravail arr = new ArretTravail();
			
			/// Historic ///
			HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
			Historique hs = new Historique();
			
			hs.setType_histo("CONSULTATION_Arret_Travail");
			hs.setDetails("ID_Arret_Travail : " + id);
			hs.setID_amsp(null);
			hs.setID_patient(null);
			
			h.create(hs);
			
			arr = dao.getById(id);
			
			int id_arret = id;
			String type = arr.getType();
			String nomPat = arr.getNom_patient();
			String prePat = arr.getPrenom_patient();
			String nomMed = arr.getNom_medecin();
			String preMed = arr.getPrenom_medecin();
			String debut = arr.getDate_debut();
			String fin = arr.getDate_fin();
			
			request.setAttribute("id",id_arret);
			request.setAttribute("type", type);
			request.setAttribute("nomPat", nomPat);
			request.setAttribute("prePat", prePat);
			request.setAttribute("nomMed", nomMed);
			request.setAttribute("preMed", preMed);
			request.setAttribute("debut", debut);
			request.setAttribute("fin", fin);
			request.getServletContext().getRequestDispatcher("/WEB-INF/ArretTravailQr.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
