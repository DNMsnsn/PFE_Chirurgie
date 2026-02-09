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

@WebServlet("/DeleteArretTravail")
public class DeleteArretTravail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ArretTravail arr = new ArretTravail();
		arr.setId_arret(id);
		
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			ArretTravail a = dao.find(id);
			
			if(dao.delete(arr)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Arret_Travail");
				hs.setDetails("ID_Medecin : " + a.getId_medecin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(a.getId_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListArretTravail");
			} else {
				request.setAttribute("error", "Arret de Travail non Supprimer !");
				request.setAttribute("listArret", dao.listArret());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listArretTravail.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
