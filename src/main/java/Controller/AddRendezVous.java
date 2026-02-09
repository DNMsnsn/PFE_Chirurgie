package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import Model.RendezVous;
import Model.RendezVousDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddRendezVous")
public class AddRendezVous extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/addRendezVous.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String patf = request.getParameter("pfnm");
		String pats = request.getParameter("psnm");
		String medf = request.getParameter("mfnm");
		String meds = request.getParameter("msnm");
		String dt = request.getParameter("dt");
		String motif = request.getParameter("motif");
		String stat = request.getParameter("stt");
		
		try {
			RendezVousDao dao = new RendezVousDao(GetConnected.getConnected());
			
			int idPat = dao.patientId(patf, pats);
			int idMed = dao.medecinId(medf, meds);
			
			if(idPat != 0 && idMed != 0) {
				
				RendezVous rdv = new RendezVous();
				rdv.setID_patient(idPat);
				rdv.setID_medecin(idMed);
				rdv.setDt_rdv(dt);
				rdv.setMotif_rdv(motif);
				rdv.setStatu_rdv(stat);
				
				if(dao.create(rdv)) {
					
					/// Historic ///
					HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
					Historique hs = new Historique();
					
					Object idObj = request.getSession().getAttribute("id");

					int idamsp = (idObj != null) ? (Integer) idObj : 0;
					
					hs.setType_histo("ADD_RDV");
					hs.setDetails("ID_Medecin : " + idMed);
					hs.setID_amsp(idamsp);
					hs.setID_patient(idPat);
					
					h.create(hs);
					
					response.sendRedirect("ListRendezVous");
				} else {
					request.setAttribute("error", "Rendez-Vous Non Ajouter !");
					request.setAttribute("listRdv", dao.listRdv());
					request.getServletContext()
					.getRequestDispatcher("/WEB-INF/addRendezVous.jsp")
					.forward(request, response);
				}
				
			} else {
				request.setAttribute("error", "Patient ou Médecin Introuvable !");
				request.setAttribute("listRdv", dao.listRdv());
			    request.getServletContext()
			           .getRequestDispatcher("/WEB-INF/addRendezVous.jsp")
			           .forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
