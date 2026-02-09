package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;

@WebServlet("/LogoutAmsp")
public class LogoutAmsp extends HttpServlet {
	private static final long serialVersionUID = 1L;    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    HttpSession session = request.getSession(false);

	    if (session != null && session.getAttribute("id") != null) {

	        try {
	            HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
	            Historique hs = new Historique();

	            hs.setType_histo("DISCONNECTION_AMSP");
	            hs.setDetails(
	                "Amsp Disconnected : " +
	                session.getAttribute("fnm") + " " +
	                session.getAttribute("snm")
	            );
	            hs.setID_amsp((Integer) session.getAttribute("id"));
	            hs.setID_patient(null);

	            h.create(hs);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        session.invalidate();
	    }

	    response.sendRedirect("LoginAmsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
