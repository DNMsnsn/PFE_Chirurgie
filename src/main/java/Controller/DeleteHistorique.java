package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

@WebServlet("/DeleteHistorique")
public class DeleteHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Historique h = new Historique();
		h.setID_histo(id);
		
		try {
			HistoriqueDao dao = new HistoriqueDao(GetConnected.getConnected());
			
			if(dao.delete(h)) {
				request.setAttribute("error", "Historique Supprimer avec Succes");
				request.setAttribute("listHist", dao.listHis());
				request.getServletContext().getRequestDispatcher("/WEB-INF/Historic.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Historique non Supprimer !");
				request.setAttribute("listHist", dao.listHis());
				request.getServletContext().getRequestDispatcher("/WEB-INF/Historic.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
