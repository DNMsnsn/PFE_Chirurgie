package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Diagnostic;
import Model.DiagnosticDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddDiagnostic")
public class AddDiagnostic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/addDiagnostic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fPat = request.getParameter("pfnm");
		String sPat = request.getParameter("psnm");
		String fMed = request.getParameter("mfnm");
		String sMed = request.getParameter("msnm");
		String dt = request.getParameter("dt");
		String desc = request.getParameter("desc");
		
		try {
			DiagnosticDao dao = new DiagnosticDao(GetConnected.getConnected());
			
			int idPat = dao.patientId(fPat, sPat);
			int idMed = dao.medecinId(fMed, sMed);
			
			if(idPat != 0 && idMed != 0) {
				
				Diagnostic dia = new Diagnostic();
				dia.setID_patient(idPat);
				dia.setID_medecin(idMed);
				dia.setDt_diag(dt);
				dia.setDescription_diag(desc);
				
				if(dao.create(dia)) {
					
					/// Historic ///
					HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
					Historique hs = new Historique();
					
					Object idObj = request.getSession().getAttribute("id");

					int idamsp = (idObj != null) ? (Integer) idObj : 0;
					
					hs.setType_histo("ADD_Diagnostic");
					hs.setDetails("ID_Medecin : " + idMed);
					hs.setID_amsp(idamsp);
					hs.setID_patient(idPat);
					
					h.create(hs);
					
					response.sendRedirect("ListDiagnostic");
				} else {
					request.setAttribute("error", "Diagnostique Non Ajouter !");
					request.setAttribute("listDiag", dao.listDiagnostic());
					request.getServletContext()
					.getRequestDispatcher("/WEB-INF/addDiagnostic.jsp")
					.forward(request, response);
				}
				
			} else {
				request.setAttribute("error", "Patient ou Médecin Introuvable !");
				request.setAttribute("listDiag", dao.listDiagnostic());
			    request.getServletContext()
			           .getRequestDispatcher("/WEB-INF/addDiagnostic.jsp")
			           .forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
