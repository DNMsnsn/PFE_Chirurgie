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

@WebServlet("/DeletePatient")
public class DeletePatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Patient pat = new Patient();
		pat.setID_patient(id);
		try {
			PatientDao dao = new PatientDao(GetConnected.getConnected());
			
			Patient p = dao.find(id);
			
			if(dao.delete(pat)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Patient");
				hs.setDetails("Deleted_Patient : " + p.getNom_patient() + " " + p.getPrenom_patient());
				hs.setID_amsp(idamsp);
				hs.setID_patient(p.getID_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListPatient");
			} else {
				request.setAttribute("error", "Patient non supprimer !");
				request.setAttribute("listPatient", dao.listPatient());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listPatients.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
