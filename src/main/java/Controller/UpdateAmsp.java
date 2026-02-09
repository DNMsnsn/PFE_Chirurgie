package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.Amsp;
import Model.AmspDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

@WebServlet("/UpdateAmsp")
public class UpdateAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fnm = request.getParameter("fnm");
		String snm = request.getParameter("snm");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String psw = request.getParameter("psw");
		String func = request.getParameter("func");
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			Amsp amsp = new Amsp();
			amsp.setNom_amsp(fnm);
			amsp.setPrenom_amsp(snm);
			amsp.setTel_amsp(tel);
			amsp.setEmail_amsp(mail);
			amsp.setPassword(psw);
			amsp.setFonction_amsp(func);
			amsp.setID_amsp(id);
			
			if(dao.update(amsp)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("UPDATE_AMSP");
				hs.setDetails("New Amsp : " + fnm + " " + snm);
				hs.setID_amsp(idamsp);
				hs.setID_patient(null);
				
				h.create(hs);
				
				response.sendRedirect("ListAmsp");
			}
			else {
				request.setAttribute("error", "Assistant Medicale Non Ajouter !");
				request.setAttribute("listAmsp", dao.listAmsp());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listAmsp.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
