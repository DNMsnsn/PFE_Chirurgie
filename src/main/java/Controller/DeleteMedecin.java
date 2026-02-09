package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import Model.Medecin;
import Model.MedecinDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteMedecin")
public class DeleteMedecin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Medecin med = new Medecin();
		med.setID_medecin(id);
		
		try {
			MedecinDao dao = new MedecinDao(GetConnected.getConnected());
			
			Medecin m = dao.find(id);
			
			if(dao.delete(med)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Medecin");
				hs.setDetails("Nom Medecin : " + m.getNom_medecin() + " " + m.getPrenom_medecin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(null);
				
				h.create(hs);
				
				response.sendRedirect("ListMedecin");
			} else {
				request.setAttribute("error", "Medecin non Supprime !");
				request.setAttribute("listMedecin", dao.listMedecin());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listMedecin.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
