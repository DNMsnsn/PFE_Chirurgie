package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedecinDao extends DAO<Medecin> {

	public MedecinDao(Connection cnx) {
		super(cnx);
	}

	@Override
	public Medecin find(int id) {
		
		String query = "select * from patient where id_patient = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Medecin p = new Medecin();
				p.setID_medecin(rs.getInt("id_medecin"));
				p.setNom_medecin(rs.getString("nom"));
				p.setPrenom_medecin(rs.getString("prenom"));
				
				return p;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Medecin> search(String search){
		
		String query = "select * from medecin where nom like ? or prenom like ?";
		
		List<Medecin> listMedecin = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Medecin med = new Medecin();
				med.setID_medecin(rs.getInt("id_medecin"));
				med.setNom_medecin(rs.getString("nom"));
				med.setPrenom_medecin(rs.getString("prenom"));
				med.setTel_medecin(rs.getString("telephone"));
				med.setEmail_medecin(rs.getString("email"));
				med.setSpecialite_medecin(rs.getString("specialite"));
				med.setRole_medecin(rs.getString("role"));
				
				listMedecin.add(med);
			}
			
			return listMedecin;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public List<Medecin> listMedecin(){
		
		String query = "select * from medecin;";
		
		List<Medecin> listMedecin = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Medecin med = new Medecin();
				med.setID_medecin(rs.getInt("id_medecin"));
				med.setNom_medecin(rs.getString("nom"));
				med.setPrenom_medecin(rs.getString("prenom"));
				med.setTel_medecin(rs.getString("telephone"));
				med.setEmail_medecin(rs.getString("email"));
				med.setSpecialite_medecin(rs.getString("specialite"));
				med.setRole_medecin(rs.getString("role"));
				
				listMedecin.add(med);
			}
			
			return listMedecin;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean create(Medecin obj) {
		
		String query = "insert into medecin(nom,prenom,specialite,telephone,email,role) values(?,?,?,?,?,?);";
		
		try {
			
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getNom_medecin().toUpperCase());
			ps.setString(2, obj.getPrenom_medecin().toLowerCase());
			ps.setString(3, obj.getSpecialite_medecin());
			ps.setString(4, obj.getTel_medecin());
			ps.setString(5, obj.getEmail_medecin());
			ps.setString(6, obj.getRole_medecin());
			
			int row = ps.executeUpdate();
			
			return row > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Medecin obj) {
		
		String query = "update medecin set nom = ?, prenom = ?, specialite = ?, "
				+ "telephone = ?, email = ?, role = ? where id_medecin = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getNom_medecin().toUpperCase());
			ps.setString(2, obj.getPrenom_medecin().toLowerCase());
			ps.setString(3, obj.getSpecialite_medecin());
			ps.setString(4, obj.getTel_medecin());
			ps.setString(5, obj.getEmail_medecin());
			ps.setString(6, obj.getRole_medecin());
			ps.setInt(7, obj.getID_medecin());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Medecin obj) {
		
		String query = "delete from medecin where id_medecin = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_medecin());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
