package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Amsp;
import Model.AmspDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteAmsp")
public class DeleteAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Amsp a = new Amsp();
		a.setID_amsp(id);
		
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			Amsp aa = dao.find(id);
			
			if(dao.delete(a)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_AMSP");
				hs.setDetails("Name : " + aa.getNom_amsp() + " " + aa.getPrenom_amsp());
				hs.setID_amsp(idamsp);
				hs.setID_patient(null);
				
				h.create(hs);
				
				response.sendRedirect("ListAmsp");
			} else {
				request.setAttribute("error", "Assistant non Supprimer");
				request.setAttribute("listAmsp", dao.listAmsp());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listAmsp.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
