package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.Crh;
import Model.CrhDao;
import Model.GetConnected;

@WebServlet("/DeleteCrh")
public class DeleteCrh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_crh = Integer.parseInt(request.getParameter("id"));
		
		
		try {
			CrhDao dao = new CrhDao(GetConnected.getConnected());
			
			Crh c = new Crh();
			c.setId_crh(id_crh);
			
			if(dao.delete(c)) {
				response.sendRedirect("ListCrh");
			} else {
				request.setAttribute("error", "CRH non Supprimer !");
				request.setAttribute("listCrh", dao.listCrh());
				request.getServletContext().getRequestDispatcher("/WEB-INF/crh.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
