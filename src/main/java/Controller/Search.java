package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Model.Amsp;
import Model.AmspDao;
import Model.ArretTravail;
import Model.ArretTravailDao;
import Model.CertificatMedical;
import Model.CertificatMedicalDao;
import Model.CertificatSejour;
import Model.CertificatSejourDao;
import Model.Diagnostic;
import Model.DiagnosticDao;
import Model.GetConnected;
import Model.Medecin;
import Model.MedecinDao;
import Model.Patient;
import Model.PatientDao;
import Model.RendezVous;
import Model.RendezVousDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String select = request.getParameter("choose");
		
		if(search != null && search.trim().length() > 2) {
			switch (select) {
			case "AMSP" :
				amsp(search.trim(), request, response);
				break;
			case "Patient" :
				patient(search.trim(), request, response);
				break;
			case "Medecin" :
				medecin(search.trim(), request, response);
				break;
			case "RDV" : 
				rendezVous(search.trim(), request, response);
				break;
			case "Diagnostic" :
				diagnostic(search.trim(), request, response);
				break;
			case "Arret" :
				arret(search.trim(), request, response);
				break;
			case "Certificat" : 
				certificat(search.trim(), request, response);
				break;
			case "Titre" : 
				titre(search.trim(), request, response);
				break;
			default :
				patient(search.trim(), request, response);
				break;
			}
		}
		
	}
	
	public void amsp(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			List<Amsp> amsp = dao.search(search);
			
			request.setAttribute("listAmsp", amsp);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listAmsp.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void patient(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PatientDao dao = new PatientDao(GetConnected.getConnected());
			
			List<Patient> patient = dao.search(search);
			
			request.setAttribute("listPatient", patient);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listPatients.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void medecin(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MedecinDao dao = new MedecinDao(GetConnected.getConnected());
			
			List<Medecin> medecin = dao.search(search);
			
			request.setAttribute("listMedecin", medecin);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listMedecin.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rendezVous(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RendezVousDao dao = new RendezVousDao(GetConnected.getConnected());
			
			List<RendezVous> rdv = dao.search(search);
			
			request.setAttribute("listRdv", rdv);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listRendezVous.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void diagnostic(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DiagnosticDao dao = new DiagnosticDao(GetConnected.getConnected());
			
			List<Diagnostic> diag = dao.search(search);
			
			request.setAttribute("listDiag", diag);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listDiagnostic.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void arret(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			
			List<ArretTravail> arr = dao.search(search);
			
			request.setAttribute("listArret", arr);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listArretTravail.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void certificat(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CertificatMedicalDao dao = new CertificatMedicalDao(GetConnected.getConnected());
			
			List<CertificatMedical> cert = dao.search(search);
			
			request.setAttribute("listCertificat", cert);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatMedical.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void titre(String search,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CertificatSejourDao dao = new CertificatSejourDao(GetConnected.getConnected());
			
			List<CertificatSejour> sej = dao.search(search);
			
			request.setAttribute("listSejour", sej);
			request.getServletContext().getRequestDispatcher("/WEB-INF/listCertificatSejour.jsp").forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
