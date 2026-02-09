package Controller;

import java.io.IOException;

import java.sql.SQLException;

import Model.DiagnosticDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListDiagnostic")
public class ListDiagnostic extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			DiagnosticDao dao = new DiagnosticDao(GetConnected.getConnected());
			
			request.setAttribute("listDiag", dao.listDiagnostic());
			request.getServletContext().getRequestDispatcher("/WEB-INF/listDiagnostic.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
