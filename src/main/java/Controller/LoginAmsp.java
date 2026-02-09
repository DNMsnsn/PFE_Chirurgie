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
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginAmsp")
public class LoginAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/loginAmsp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("fnm");
		String psw = request.getParameter("psw");
		
		try {
			AmspDao dao = new AmspDao(GetConnected.getConnected());
			
			if(dao.exists(nom, psw)) {
				
				Amsp amsp = dao.select(nom);
				
				HttpSession session = request.getSession();

				session.setAttribute("id", amsp.getID_amsp());
				session.setAttribute("fnm", amsp.getNom_amsp());
				session.setAttribute("snm", amsp.getPrenom_amsp());
				session.setAttribute("mail", amsp.getEmail_amsp());
				session.setAttribute("tel", amsp.getTel_amsp());
				session.setAttribute("func", amsp.getFonction_amsp());
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				hs.setType_histo("CNX_AMSP");
				hs.setDetails("Amsp Connected : " + amsp.getNom_amsp() + " " + amsp.getPrenom_amsp());
				hs.setID_amsp(amsp.getID_amsp());
				hs.setID_patient(null);
				
				h.create(hs);
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
			} else {
				request.setAttribute("error", "Invalid Password or Username !");
				request.getServletContext().getRequestDispatcher("/WEB-INF/loginAmsp.jsp")
				.forward(request, response);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
