package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Diagnostic;
import Model.DiagnosticDao;
import Model.GetConnected;
import Model.Historique;
import Model.HistoriqueDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteDiagnostic")
public class DeleteDiagnostic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			DiagnosticDao dao = new DiagnosticDao(GetConnected.getConnected());
			
			Diagnostic d = dao.find(id);
			
			if(dao.deleteDiag(id)) {
				
				/// Historic ///
				HistoriqueDao h = new HistoriqueDao(GetConnected.getConnected());
				Historique hs = new Historique();
				
				Object idObj = request.getSession().getAttribute("id");

				int idamsp = (idObj != null) ? (Integer) idObj : 0;
				
				hs.setType_histo("DELETE_Diagnostic");
				hs.setDetails("ID_Medecin : " + d.getID_medecin());
				hs.setID_amsp(idamsp);
				hs.setID_patient(d.getID_patient());
				
				h.create(hs);
				
				response.sendRedirect("ListDiagnostic");
			} else {
				request.setAttribute("error", "Rendez Vous Non Supprimer !");
				request.setAttribute("listDiag", dao.listDiagnostic());
				request.getServletContext().getRequestDispatcher("/WEB-INF/listDiagnostic").forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
