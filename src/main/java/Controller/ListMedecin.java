package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.MedecinDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListMedecin")
public class ListMedecin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MedecinDao dao = new MedecinDao(GetConnected.getConnected());
			
			request.setAttribute("listMedecin", dao.listMedecin());
			request.getServletContext()
			.getRequestDispatcher("/WEB-INF/listMedecin.jsp")
			.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
