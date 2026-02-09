package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.CertificatSejourDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListCertificatSejour")
public class ListCertificatSejour extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
			
			request.setAttribute("listSejour", dao.listSejour());
			request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatSejour.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
