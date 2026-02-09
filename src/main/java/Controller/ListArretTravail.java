package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.ArretTravailDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListArretTravail")
public class ListArretTravail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			
			request.setAttribute("listArret", dao.listArret());
			request.getServletContext().getRequestDispatcher("/WEB-INF/listArretTravail.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
