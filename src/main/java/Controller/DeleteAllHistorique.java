package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.HistoriqueDao;

@WebServlet("/DeleteAllHistorique")
public class DeleteAllHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HistoriqueDao dao = new HistoriqueDao(GetConnected.getConnected());
			
			if(dao.deleteAll()) {
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
