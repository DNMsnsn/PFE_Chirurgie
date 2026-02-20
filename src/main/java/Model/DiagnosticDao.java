package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticDao extends DAO<Diagnostic> {

	public DiagnosticDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public Diagnostic find(int id) {

		String query = "select * from diagnostic where id_diagnostic = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Diagnostic d = new Diagnostic();
				d.setID_diagnostic(rs.getInt("id_diagnostic"));
				d.setDt_diag(rs.getString("date_diagnostic"));
				d.setID_medecin(rs.getInt("id_medecin"));
				d.setID_patient(rs.getInt("id_patient"));
				
				return d;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Diagnostic();
	}
	
	public List<Diagnostic> search(String search){
		
		String query = "SELECT "
				+ "d.id_diagnostic, d.date_diagnostic, d.description,"
				+ "p.nom AS patient_nom, p.prenom AS patient_prenom, "
				+ "m.nom AS medecin_nom, m.prenom AS medecin_prenom "
				+ "FROM diagnostic d "
				+ "JOIN patient p "
				+ "ON d.id_patient = p.id_patient "
				+ "JOIN medecin m "
				+ "ON d.id_medecin = m.id_medecin "
				+ "where p.nom like ? or p.prenom like ?";
		
		List<Diagnostic> listDiag = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Diagnostic dia = new Diagnostic();
				dia.setID_diagnostic(rs.getInt("id_diagnostic"));
				dia.setDt_diag(rs.getString("date_diagnostic"));
				dia.setDescription_diag(rs.getString("description"));
				dia.setFnmPatient(rs.getString("patient_nom"));
				dia.setSnmPatient(rs.getString("patient_prenom"));
				dia.setFnmMedecin(rs.getString("medecin_nom"));
				dia.setSnmMedecin(rs.getString("medecin_prenom"));
				
				listDiag.add(dia);
			}
			
			return listDiag;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<Diagnostic>();
	}
	
	public List<Diagnostic> listDiagnostic(){
		
		String query = "SELECT "
				+ "d.id_diagnostic, d.date_diagnostic, d.description,"
				+ "p.nom AS patient_nom, p.prenom AS patient_prenom, "
				+ "m.nom AS medecin_nom, m.prenom AS medecin_prenom "
				+ "FROM diagnostic d "
				+ "JOIN patient p "
				+ "ON d.id_patient = p.id_patient "
				+ "JOIN medecin m "
				+ "ON d.id_medecin = m.id_medecin;";
		
		List<Diagnostic> listDiag = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Diagnostic dia = new Diagnostic();
				dia.setID_diagnostic(rs.getInt("id_diagnostic"));
				dia.setDt_diag(rs.getString("date_diagnostic"));
				dia.setDescription_diag(rs.getString("description"));
				dia.setFnmPatient(rs.getString("patient_nom"));
				dia.setSnmPatient(rs.getString("patient_prenom"));
				dia.setFnmMedecin(rs.getString("medecin_nom"));
				dia.setSnmMedecin(rs.getString("medecin_prenom"));
				
				listDiag.add(dia);
			}
			
			return listDiag;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<Diagnostic>();
	}
	
	public int patientId(String fnm, String snm) {
		
		String query = "select id_patient from patient where nom = ? and prenom = ?";
		
		try {
			
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, fnm.toUpperCase());
			ps.setString(2, snm.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id_patient");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int medecinId(String fnm, String snm) {
		
		String query = "select id_medecin from medecin where nom = ? and prenom = ?";
		
		try {
			
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, fnm.toUpperCase());
			ps.setString(2, snm.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id_medecin");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean create(Diagnostic obj) {
		
		String query = "insert into diagnostic(id_patient,id_medecin,date_diagnostic,description)"
				+ "values(?,?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_patient());
			ps.setInt(2, obj.getID_medecin());
			ps.setString(3, obj.getDt_diag());
			ps.setString(4, obj.getDescription_diag());
			
			int rs = ps.executeUpdate();
			
			return rs > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Diagnostic obj) {
		
		String query = "update diagnostic set description = ?, date_diagnostic = ?, id_patient = ?, id_medecin = ? where id_diagnostic = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getDescription_diag());
			ps.setString(2, obj.getDt_diag());
			ps.setInt(3, obj.getID_patient());
			ps.setInt(4, obj.getID_medecin());
			ps.setInt(5, obj.getID_diagnostic());
			
			int rs = ps.executeUpdate();
			
			return rs > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Diagnostic obj) {
		
		return false;
	}
	
	public boolean deleteDiag(int id) {
		
		String query = "delete from diagnostic where id_diagnostic = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			ps.setInt(1, id);
			
			int rs = ps.executeUpdate();
			
			return rs > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
