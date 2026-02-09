package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Amsp;
import Model.AmspDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddAmsp")
public class AddAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/WEB-INF/addAmsp.jsp").forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fnm = request.getParameter("fnm");
		String snm = request.getParameter("snm");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String psw = request.getParameter("psw");
		String func = request.getParameter("func");
		
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			Amsp amsp = new Amsp();
			amsp.setNom_amsp(fnm);
			amsp.setPrenom_amsp(snm);
			amsp.setTel_amsp(tel);
			amsp.setEmail_amsp(mail);
			amsp.setPassword(psw);
			amsp.setFonction_amsp(func);
			
			if(dao.create(amsp)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("ADD_AMSP");
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
