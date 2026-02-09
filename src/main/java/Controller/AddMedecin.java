package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import Model.Medecin;
import Model.MedecinDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddMedecin")
public class AddMedecin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/addMedecin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fnm = request.getParameter("fnm");
		String snm = request.getParameter("snm");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String spec = request.getParameter("spc");
		String role = request.getParameter("role");
		
		try {
			MedecinDao dao = new MedecinDao(GetConnected.getConnected());
			
			Medecin med = new Medecin();
			med.setNom_medecin(fnm);
			med.setPrenom_medecin(snm);
			med.setEmail_medecin(mail);
			med.setTel_medecin(tel);
			med.setSpecialite_medecin(spec);
			med.setRole_medecin(role);
			
			if(dao.create(med)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("ADD_Medecin");
				hs.setDetails("New Medecin : " + fnm + " " + snm);
				hs.setID_amsp(idamsp);
				hs.setID_patient(null);
				
				h.create(hs);
				
				response.sendRedirect("ListMedecin");
			} else {
				request.setAttribute("error", "Medecin non Ajouter !");
				request.setAttribute("listMedecin", dao.listMedecin());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listMedecin.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
