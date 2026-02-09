package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Cro;
import Model.CroDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCro")
public class DeleteCro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			CroDao dao = new CroDao(GetConnected.getConnected());
			
			Cro c = new Cro();
			c.setId_cro(id);
			
			if(dao.delete(c)) {
				response.sendRedirect("ListCro");
			} else {
				request.setAttribute("error", "Protocole non Supprimer !");
				request.getServletContext().getRequestDispatcher("/WEB-INF/cro.jsp").forward(request, response);
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
