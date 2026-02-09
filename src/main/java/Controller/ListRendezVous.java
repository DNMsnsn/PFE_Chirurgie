package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.RendezVousDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListRendezVous")
public class ListRendezVous extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			RendezVousDao dao = new RendezVousDao(GetConnected.getConnected());
			
			request.setAttribute("listRdv", dao.listRdv());
			request.getServletContext().getRequestDispatcher("/WEB-INF/listRendezVous.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
