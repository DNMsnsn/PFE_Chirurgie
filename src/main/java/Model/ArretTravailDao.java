package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArretTravailDao extends DAO<ArretTravail> {

	public ArretTravailDao(Connection cnx) {
		super(cnx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArretTravail find(int id) {
		
		String query = "select * from arret_travail where id_arret = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ArretTravail a = new ArretTravail();
				a.setDate_debut(rs.getString("date_debut"));
				a.setDate_fin(rs.getString("date_fin"));
				a.setId_arret(rs.getInt("id_arret"));
				a.setId_medecin(rs.getInt("id_medecin"));
				a.setId_patient(rs.getInt("id_patient"));
				
				return a;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArretTravail();
	}
	
	public List<ArretTravail> listById(int id){
		
		String query = "SELECT \r\n"
				+ "    t.id_arret,\r\n"
				+ "    t.type,\r\n"
				+ "    t.date_debut,\r\n"
				+ "    t.date_fin,\r\n"
				+ "    p.nom     AS nom_patient,\r\n"
				+ "    p.prenom  AS prenom_patient,\r\n"
				+ "    m.nom     AS nom_medecin,\r\n"
				+ "    m.prenom  AS prenom_medecin\r\n"
				+ "FROM arret_travail t\r\n"
				+ "JOIN patient p\r\n"
				+ "    ON t.id_patient = p.id_patient\r\n"
				+ "JOIN medecin m\r\n"
				+ "    ON t.id_medecin = m.id_medecin\r\n"
				+ "where p.id_patient = ?";
		
		List<ArretTravail> listArret = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ArretTravail arr = new ArretTravail();
				arr.setId_arret(rs.getInt("id_arret"));
				arr.setType(rs.getString("type"));
				arr.setDate_debut(rs.getString("date_debut"));
				arr.setDate_fin(rs.getString("date_fin"));
				arr.setNom_patient(rs.getString("nom_patient"));
				arr.setPrenom_patient(rs.getString("prenom_patient"));
				arr.setNom_medecin(rs.getString("nom_medecin"));
				arr.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				listArret.add(arr);
			}
			
			return listArret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<ArretTravail>();
	}
	
	public List<ArretTravail> search(String search){
		
		String query = "SELECT \r\n"
				+ "    t.id_arret,\r\n"
				+ "    t.type,\r\n"
				+ "    t.date_debut,\r\n"
				+ "    t.date_fin,\r\n"
				+ "    p.nom     AS nom_patient,\r\n"
				+ "    p.prenom  AS prenom_patient,\r\n"
				+ "    m.nom     AS nom_medecin,\r\n"
				+ "    m.prenom  AS prenom_medecin\r\n"
				+ "FROM arret_travail t\r\n"
				+ "JOIN patient p\r\n"
				+ "    ON t.id_patient = p.id_patient\r\n"
				+ "JOIN medecin m\r\n"
				+ "    ON t.id_medecin = m.id_medecin\r\n"
				+ "where p.nom like ? or p.prenom like ?";
		
		List<ArretTravail> listArret = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ArretTravail arr = new ArretTravail();
				arr.setId_arret(rs.getInt("id_arret"));
				arr.setType(rs.getString("type"));
				arr.setDate_debut(rs.getString("date_debut"));
				arr.setDate_fin(rs.getString("date_fin"));
				arr.setNom_patient(rs.getString("nom_patient"));
				arr.setPrenom_patient(rs.getString("prenom_patient"));
				arr.setNom_medecin(rs.getString("nom_medecin"));
				arr.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				listArret.add(arr);
			}
			
			return listArret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<ArretTravail>();
	}
	
	public List<ArretTravail> listArret(){
		
		String query = "SELECT \r\n"
				+ "    t.id_arret,\r\n"
				+ "    t.type,\r\n"
				+ "    t.date_debut,\r\n"
				+ "    t.date_fin,\r\n"
				+ "    p.nom     AS nom_patient,\r\n"
				+ "    p.prenom  AS prenom_patient,\r\n"
				+ "    m.nom     AS nom_medecin,\r\n"
				+ "    m.prenom  AS prenom_medecin\r\n"
				+ "FROM arret_travail t\r\n"
				+ "JOIN patient p\r\n"
				+ "    ON t.id_patient = p.id_patient\r\n"
				+ "JOIN medecin m\r\n"
				+ "    ON t.id_medecin = m.id_medecin;\r\n"
				+ "";
		
		List<ArretTravail> listArret = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ArretTravail arr = new ArretTravail();
				arr.setId_arret(rs.getInt("id_arret"));
				arr.setType(rs.getString("type"));
				arr.setDate_debut(rs.getString("date_debut"));
				arr.setDate_fin(rs.getString("date_fin"));
				arr.setNom_patient(rs.getString("nom_patient"));
				arr.setPrenom_patient(rs.getString("prenom_patient"));
				arr.setNom_medecin(rs.getString("nom_medecin"));
				arr.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				listArret.add(arr);
			}
			
			return listArret;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArretTravail getById(int id) {
		
		String query = "SELECT \r\n"
				+ "    t.type,\r\n"
				+ "    t.date_debut,\r\n"
				+ "    t.date_fin,\r\n"
				+ "    p.nom     AS nom_patient,\r\n"
				+ "    p.prenom  AS prenom_patient,\r\n"
				+ "    p.date_naissance as date_naiss_patient,\r\n"
				+ "    m.nom     AS nom_medecin,\r\n"
				+ "    m.prenom  AS prenom_medecin\r\n"
				+ "FROM arret_travail t\r\n"
				+ "JOIN patient p\r\n"
				+ "    ON t.id_patient = p.id_patient\r\n"
				+ "JOIN medecin m\r\n"
				+ "    ON t.id_medecin = m.id_medecin\r\n"
				+ "WHERE t.id_arret = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ArretTravail arr = new ArretTravail();
				arr.setType(rs.getString("type"));
				arr.setDate_debut(rs.getString("date_debut"));
				arr.setDate_fin(rs.getString("date_fin"));
				arr.setNom_patient(rs.getString("nom_patient"));
				arr.setPrenom_patient(rs.getString("prenom_patient"));
				arr.setDate_naiss_patient(rs.getString("date_naiss_patient"));
				arr.setNom_medecin(rs.getString("nom_medecin"));
				arr.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				return arr;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int idPatient(String fnm, String snm) {
		
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
	
	public int idMedecin(String fnm, String snm) {
		
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
	public boolean create(ArretTravail obj) {
		String query = "insert into arret_travail(type,date_debut,date_fin,id_patient,id_medecin) values(?,?,?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getType());
			ps.setDate(2, Date.valueOf(obj.getDate_debut()));
			ps.setDate(3, Date.valueOf(obj.getDate_fin()));
			ps.setInt(4, obj.getId_patient());
			ps.setInt(5, obj.getId_medecin());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(ArretTravail obj) {
		
		String query = "update arret_travail set type = ?, date_debut = ?, date_fin = ?, id_patient = ?, id_medecin = ? where id_arret = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getType());
			ps.setString(2, obj.getDate_debut());
			ps.setString(3, obj.getDate_fin());
			ps.setInt(4, obj.getId_patient());
			ps.setInt(5, obj.getId_medecin());
			ps.setInt(6, obj.getId_arret());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(ArretTravail obj) {
		
		String query = "delete from arret_travail where id_arret = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_arret());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
