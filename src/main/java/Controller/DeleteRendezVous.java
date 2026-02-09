package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import Model.RendezVous;
import Model.RendezVousDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteRendezVous")
public class DeleteRendezVous extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			RendezVousDao dao = new RendezVousDao(GetConnected.getConnected());
			
			RendezVous r = dao.find(id);
			
			if(dao.deleteRdv(id)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_RDV");
				hs.setDetails("ID_Medecin : " + r.getID_medecin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(r.getID_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListRendezVous");
			} else {
				request.setAttribute("error", "Rendez Vous Non Supprimer !");
				request.setAttribute("listRdv", dao.listRdv());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listRendezVous").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
