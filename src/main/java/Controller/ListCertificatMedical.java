package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CertificatMedicalDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListCertificatMedical")
public class ListCertificatMedical extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CertificatMedicalDao dao = new CertificatMedicalDao(GetConnected.getConnected());
			
			request.setAttribute("listCertificat", dao.listCertificat());
			request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatMedical.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
