package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import Model.Patient;
import Model.PatientDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePatient")
public class UpdatePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String fnm = request.getParameter("fnm");
		String snm = request.getParameter("snm");
		String dt = request.getParameter("dt");
		String sexe = request.getParameter("sexe");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String adrs = request.getParameter("adrs");
		String blood = request.getParameter("blood");
		
		Patient pat = new Patient();
		pat.setID_patient(id);
		pat.setNom_patient(fnm);
		pat.setPrenom_patient(snm);
		pat.setDt_naiss_patient(dt);
		pat.setSexe_patient(sexe);
		pat.setTel_patient(tel);
		pat.setEmail_patient(mail);
		pat.setAdresse_patient(adrs);
		pat.setGrp_sang_patient(blood);
		
		try {
			PatientDao dao = new PatientDao(GetConnected.getConnected());
			
			if(dao.update(pat)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("UPDATE_Patient");
				hs.setDetails("New Patient : " + fnm + " " + snm);
				hs.setID_amsp(idamsp);
				hs.setID_patient(null);
				
				h.create(hs);
				
				response.sendRedirect("ListPatient");
			} else {
				request.setAttribute("error", "Patient non Modifier");
				request.setAttribute("listPatient", dao.listPatient());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listPatients.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
