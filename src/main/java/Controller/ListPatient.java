package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.PatientDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListPatient")
public class ListPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PatientDao dao = new PatientDao(GetConnected.getConnected());
			
			request.setAttribute("listPatient", dao.listPatient());
			request.getServletContext()
			.getRequestDispatcher("/WEB-INF/listPatients.jsp")
			.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
