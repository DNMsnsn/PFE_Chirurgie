package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.AmspDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListAmsp")
public class ListAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			request.setAttribute("listAmsp", dao.listAmsp());
			request.getServletContext()
			.getRequestDispatcher("/WEB-INF/listAmsp.jsp")
			.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
