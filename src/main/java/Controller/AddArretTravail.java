package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.ArretTravail;
import Model.ArretTravailDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

@WebServlet("/AddArretTravail")
public class AddArretTravail extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String pfnm = request.getParameter("pfnm");
		String psnm = request.getParameter("psnm");
		String mfnm = request.getParameter("mfnm");
		String msnm = request.getParameter("msnm");
		
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			
			int idPatient = dao.idPatient(pfnm, psnm);
			int idMedecin = dao.idMedecin(mfnm, msnm);
			
			
			
			if(idPatient != 0 && idMedecin != 0) {
				
				ArretTravail arr = new ArretTravail();
				arr.setId_patient(idPatient);
				arr.setId_medecin(idMedecin);
				arr.setDate_debut(debut);
				arr.setDate_fin(fin);
				arr.setType(type);
				
				if(dao.create(arr)) {
					
					/// Historic ///
					HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
					Historique hs = new Historique();
					
					Object idObj = request.getSession().getAttribute("id");

					int idamsp = (idObj != null) ? (Integer) idObj : 0;
					
					hs.setType_histo("ADD_Arret_Travail");
					hs.setDetails("ID_Medecin : " + idMedecin);
					hs.setID_amsp(idamsp);
					hs.setID_patient(idPatient);
					
					h.create(hs);
					
					response.sendRedirect("ListArretTravail");
				} else {
					request.setAttribute("error", "Arret de Travail non Ajouter !");
					request.setAttribute("listArret", dao.listArret());
					request.getServletContext().getRequestDispatcher("/WEB-INF/listArretTravail.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Patient ou Medecin Introuvable !");
				request.setAttribute("listArret", dao.listArret());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listArretTravail.jsp").forward(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
