package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Crh;
import Model.CrhDao;
import Model.GetConnected;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddCrh")
public class AddCrh extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int id_crh = Integer.parseInt(request.getParameter(""));
		String np = request.getParameter("n_patient");
		String pp = request.getParameter("p_patient");
		String nm = request.getParameter("n_medecin");
		String pm = request.getParameter("p_medecin");
		String dt = request.getParameter("dte");
		String ds = request.getParameter("dts");
		String cnt = request.getParameter("content");
		
		try {
			CrhDao dao = new CrhDao(GetConnected.getConnected());
			
			Crh c = new Crh();
			c.setId_patient(dao.idPatient(np, pp));
			c.setId_medecin(dao.idMedecin(nm, pm));
			c.setDate_entre(dt);
			c.setDate_sortie(ds);
			c.setContent(cnt);
			
			if(dao.create(c)) {
				response.sendRedirect("ListCrh");
			} else {
				request.setAttribute("error", "CRH non Ajouter !");
				request.setAttribute("listCrh", dao.listCrh());
				request.getServletContext().getRequestDispatcher("/WEB-INF/crh.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
