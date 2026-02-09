package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrhDao extends DAO<Crh> {

	public CrhDao(Connection cnx) {
		super(cnx);
	}

	@Override
	public Crh find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int idPatient(String fnm, String snm) {
		
		String query = "select id_patient from patient where nom = ? and prenom = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, fnm);
			ps.setString(2, snm);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id_patient");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int idMedecin(String fnm, String snm) {
		
		String query = "select id_medecin from medecin where nom = ? and prenom = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, fnm);
			ps.setString(2, snm);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("id_medecin");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public List<Crh> listCrh(){
		
		String query = "select c.id_crh,c.id_patient,c.id_medecin,c.content,c.date_entree,c.date_sortie,\r\n"
				+ "    p.nom as nom_patient,\r\n"
				+ "    p.prenom as prenom_patient,\r\n"
				+ "    p.date_naissance as dt_patient,\r\n"
				+ "    m.nom as nom_medecin,\r\n"
				+ "    m.prenom as prenom_medecin\r\n"
				+ "    from crh c\r\n"
				+ "    join patient p\r\n"
				+ "    on c.id_patient = p.id_patient\r\n"
				+ "    join medecin m\r\n"
				+ "    on c.id_medecin = m.id_medecin;";
		
		List<Crh> list = new ArrayList<>();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Crh c = new Crh();
				c.setId_crh(rs.getInt("id_crh"));
				c.setId_patient(rs.getInt("id_patient"));
				c.setId_medecin(rs.getInt("id_medecin"));
				c.setNom_patient(rs.getString("nom_patient"));
				c.setPrenom_patient(rs.getString("prenom_patient"));
				c.setDate_naiss_patient(rs.getString("dt_patient"));
				c.setNom_medecin(rs.getString("nom_medecin"));
				c.setPrenom_medecin(rs.getString("prenom_medecin"));
				c.setDate_entre(rs.getString("date_entree"));
				c.setDate_sortie(rs.getString("date_sortie"));
				c.setContent(rs.getString("content"));
				
				list.add(c);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	public List<Crh> getById(int id){
		
		String query = "select c.id_crh,c.id_patient,c.id_medecin,c.content,c.date_entree,c.date_sortie,\r\n"
				+ "	    p.nom as nom_patient,\r\n"
				+ "	    p.prenom as prenom_patient,\r\n"
				+ "	    p.date_naissance as dt_patient,\r\n"
				+ "	    m.nom as nom_medecin,\r\n"
				+ "	    m.prenom as prenom_medecin\r\n"
				+ "	    from crh c\r\n"
				+ "	    join patient p\r\n"
				+ "	    on c.id_patient = p.id_patient\r\n"
				+ "	    join medecin m\r\n"
				+ "	    on c.id_medecin = m.id_medecin \r\n"
				+ "	    where c.id_patient = ?;";
		
		List<Crh> list = new ArrayList<>();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Crh c = new Crh();
				c.setId_crh(rs.getInt("id_crh"));
				c.setId_patient(rs.getInt("id_patient"));
				c.setId_medecin(rs.getInt("id_medecin"));
				c.setNom_patient(rs.getString("nom_patient"));
				c.setPrenom_patient(rs.getString("prenom_patient"));
				c.setDate_naiss_patient(rs.getString("dt_patient"));
				c.setNom_medecin(rs.getString("nom_medecin"));
				c.setPrenom_medecin(rs.getString("prenom_medecin"));
				c.setDate_entre(rs.getString("date_entree"));
				c.setDate_sortie(rs.getString("date_sortie"));
				c.setContent(rs.getString("content"));
				
				list.add(c);
			}
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	@Override
	public boolean create(Crh obj) {
		
		String query = "insert into crh(id_patient,id_medecin,content,date_entree,date_sortie)\r\n"
				+ "    	values(?,?,?,?,?); ";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_patient());
			ps.setInt(2, obj.getId_medecin());
			ps.setString(3, obj.getContent());
			ps.setString(4, obj.getDate_entre());
			ps.setString(5, obj.getDate_sortie());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Crh obj) {
		
		String query = "update crh set id_patient = ?,id_medecin = ?,content = ?,date_entree = ?,date_sortie = ? "
				+ "where id_crh = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_patient());
			ps.setInt(2, obj.getId_medecin());
			ps.setString(3, obj.getContent());
			ps.setString(4, obj.getDate_entre());
			ps.setString(5, obj.getDate_sortie());
			ps.setInt(6, obj.getId_crh());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Crh obj) {
		
		String query = "delete from crh where id_crh = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_crh());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
