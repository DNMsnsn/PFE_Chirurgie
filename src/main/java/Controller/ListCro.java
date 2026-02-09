package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CroDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListCro")
public class ListCro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			CroDao dao = new CroDao(GetConnected.getConnected());
			
			request.setAttribute("listCro", dao.listCro());
			request.getServletContext().getRequestDispatcher("/WEB-INF/cro.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
