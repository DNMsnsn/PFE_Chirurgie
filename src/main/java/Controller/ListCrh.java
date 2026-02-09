package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CrhDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListCrh")
public class ListCrh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			CrhDao dao = new CrhDao(GetConnected.getConnected());
			
			request.setAttribute("listCrh", dao.listCrh());
			request.getServletContext().getRequestDispatcher("/WEB-INF/crh.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
