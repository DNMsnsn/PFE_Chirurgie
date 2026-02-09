package Controller;

import java.io.IOException;

import java.sql.SQLException;

import Model.GetConnected;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListHistorique")
public class ListHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HistoriqueDao dao = new HistoriqueDao(GetConnected.getConnected());
			
			request.setAttribute("listHist", dao.listHis());
			request.getServletContext().getRequestDispatcher("/WEB-INF/Historic.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
