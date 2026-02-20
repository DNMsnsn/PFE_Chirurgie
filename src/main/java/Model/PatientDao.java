package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao extends DAO<Patient> {
	
	public PatientDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public Patient find(int id) {
		
		String query = "select * from patient where id_patient = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Patient p = new Patient();
				p.setID_patient(rs.getInt("id_patient"));
				p.setNom_patient(rs.getString("nom"));
				p.setPrenom_patient(rs.getString("prenom"));
				p.setTel_patient(rs.getString("telephone"));
				p.setEmail_patient(rs.getString("email"));
				p.setAdresse_patient(rs.getString("adresse"));
				p.setDt_naiss_patient(rs.getString("date_naissance"));
				p.setGrp_sang_patient(rs.getString("groupe_sanguin"));
				
				return p;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Patient();
	}
	
	public List<Patient> search(String search){
		
		String query = "select * from patient where nom like ? or prenom like ?";
		
		List<Patient> listPatient = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Patient pat = new Patient();
				pat.setID_patient(rs.getInt("id_patient"));
				pat.setNom_patient(rs.getString("nom"));
				pat.setPrenom_patient(rs.getString("prenom"));
				pat.setTel_patient(rs.getString("telephone"));
				pat.setEmail_patient(rs.getString("email"));
				pat.setAdresse_patient(rs.getString("adresse"));
				pat.setDt_naiss_patient(rs.getString("date_naissance"));
				pat.setGrp_sang_patient(rs.getString("groupe_sanguin"));
				
				listPatient.add(pat);
			}
			
			return listPatient;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<>();
	}

	public List<Patient> listPatient(){
		
		String query = "select * from patient;";
		
		List<Patient> listPatient = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Patient pat = new Patient();
				pat.setID_patient(rs.getInt("id_patient"));
				pat.setNom_patient(rs.getString("nom"));
				pat.setPrenom_patient(rs.getString("prenom"));
				pat.setTel_patient(rs.getString("telephone"));
				pat.setEmail_patient(rs.getString("email"));
				pat.setAdresse_patient(rs.getString("adresse"));
				pat.setDt_naiss_patient(rs.getString("date_naissance"));
				pat.setGrp_sang_patient(rs.getString("groupe_sanguin"));
				
				listPatient.add(pat);
			}
			
			return listPatient;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<Patient>();
	}
	
	@Override
	public boolean create(Patient obj) {
		
		try {
			
			PreparedStatement ps = this.cnx.prepareStatement(
					"insert into patient(nom, prenom, date_naissance, sexe, telephone, email, adresse, groupe_sanguin)"
							+ "values(?,?,?,?,?,?,?,?);");
			
			ps.setString(1, obj.getNom_patient().toUpperCase());
			ps.setString(2, obj.getPrenom_patient().toLowerCase());
			ps.setString(3, obj.getDt_naiss_patient());
			ps.setString(4, obj.getSexe_patient());
			ps.setString(5, obj.getTel_patient());
			ps.setString(6, obj.getEmail_patient());
			ps.setString(7, obj.getAdresse_patient());
			ps.setString(8, obj.getGrp_sang_patient());
			
			int result = ps.executeUpdate();
			
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Patient obj) {
		
		String query = "update patient set nom = ?, "
				+ "prenom = ?, date_naissance = ?, "
				+ "sexe = ?, telephone = ?, email = ?, "
				+ "adresse = ?, groupe_sanguin = ? where id_patient = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getNom_patient().toUpperCase());
			ps.setString(2, obj.getPrenom_patient().toLowerCase());
			ps.setString(3, obj.getDt_naiss_patient());
			ps.setString(4, obj.getSexe_patient());
			ps.setString(5, obj.getTel_patient());
			ps.setString(6, obj.getEmail_patient());
			ps.setString(7, obj.getAdresse_patient());
			ps.setString(8, obj.getGrp_sang_patient());
			ps.setInt(9, obj.getID_patient());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Patient obj) {
		
		String query = "delete from patient where id_patient = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_patient());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
