package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.ArretTravailDao;
import Model.CertificatMedicalDao;
import Model.CertificatSejourDao;
import Model.CrhDao;
import Model.CroDao;
import Model.GetConnected;
import Model.PatientDao;
import Model.RendezVousDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InterfaceUtilisateur")
public class InterfaceUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			PatientDao daop = new PatientDao(GetConnected.getConnected());
			RendezVousDao daor = new RendezVousDao(GetConnected.getConnected());
			ArretTravailDao daoa = new ArretTravailDao(GetConnected.getConnected());
			CertificatMedicalDao daoc = new CertificatMedicalDao(GetConnected.getConnected());
			CertificatSejourDao daos = new CertificatSejourDao(GetConnected.getConnected());
			CroDao daocro = new CroDao(GetConnected.getConnected());
			CrhDao dao = new CrhDao(GetConnected.getConnected());
			
			request.setAttribute("patient", daop.find(id));
			request.setAttribute("listrdv", daor.listById(id));
			request.setAttribute("listArret", daoa.listById(id));
			request.setAttribute("listCertificat", daoc.listById(id));
			request.setAttribute("listSejour", daos.listById(id));
			request.setAttribute("listCro", daocro.getById(id));
			request.setAttribute("listCrh", dao.getById(id));
			
			request.getServletContext().getRequestDispatcher("/WEB-INF/interfacePatient.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
