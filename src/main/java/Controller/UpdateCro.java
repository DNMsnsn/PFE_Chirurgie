package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Model.Cro;
import Model.CroDao;
import Model.GetConnected;

@WebServlet("/UpdateCro")
public class UpdateCro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String np = request.getParameter("n_patient");
		String pp = request.getParameter("p_patient");
		String no = request.getParameter("n_operateur");
		String po = request.getParameter("p_operateur");
		String na = request.getParameter("n_aide");
		String pa = request.getParameter("p_aide");
		String nr = request.getParameter("n_reanimateur");
		String pr = request.getParameter("p_reanimateur");
		String nan = request.getParameter("n_anesthesiste");
		String pan = request.getParameter("p_anesthesiste");
		
		String dt = request.getParameter("dt");
		int bloc = Integer.parseInt(request.getParameter("bloc"));
		String diag = request.getParameter("diag");
		String intervention = request.getParameter("intervention");
		String protocole = request.getParameter("protocole");
		
		try {
			CroDao dao = new CroDao(GetConnected.getConnected());
			
			Cro c = new Cro();
			c.setDate_operation(dt);
			c.setBloc(bloc);
			c.setDiagnostic_lesionnel(diag);
			c.setIntervention_paratique(intervention);
			c.setProtocole(protocole);
			
			c.setId_patient(dao.idPatient(np, pp));
			c.setNom_patient(np);
			c.setPrenom_patient(pp);
			
			c.setId_operateur(dao.idOperateur(no, po));
			c.setNom_operateur(no);
			c.setPrenom_operateur(po);
			
			c.setId_aide(dao.idAide(na, pa));
			c.setNom_aide(na);
			c.setPrenom_aide(pa);
			
			c.setId_reanimateur(dao.idReanimateur(nr, pr));
			c.setNom_reanimateur(nr);
			c.setPrenom_reanimateur(pr);
			
			c.setId_anesthesiste(dao.idAnesthesiste(nan, pan));
			c.setNom_anesthesiste(nan);
			c.setPrenom_anesthesiste(pan);
			
			if(dao.update(c)) {
				response.sendRedirect("ListCro");
			} else {
				request.setAttribute("error", "Protocole non Modifier !");
				request.getServletContext().getRequestDispatcher("/WEB-INF/cro.jsp").forward(request, response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
