package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertificatSejourDao extends DAO<CertificatSejour> {

	public CertificatSejourDao(Connection cnx) {
		super(cnx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CertificatSejour find(int id) {
		
		String query = "select * from certificat_sejour where id_titre = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				CertificatSejour c = new CertificatSejour();
				c.setId_patient(rs.getInt("id_patient"));
				c.setId_titre(rs.getInt("id_titre"));
				c.setDate_debut(rs.getString("date_debut"));
				c.setDate_fin(rs.getString("date_fin"));
				
				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new CertificatSejour();
	}
	
	public List<CertificatSejour> listById(int id){
		
		String query = "select c.id_titre, c.date_debut, c.date_fin, \r\n"
				+ "		 	p.nom as nom_patient, p.prenom as prenom_patient\r\n"
				+ "    		from certificat_sejour c\r\n"
				+ "    		join patient p\r\n"
				+ "    		on c.id_patient = p.id_patient "
				+ "			where p.id_patient = ?";
		
		List<CertificatSejour> listSejour = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatSejour sej = new CertificatSejour();
				sej.setId_titre(rs.getInt("id_titre"));
				sej.setDate_debut(rs.getString("date_debut"));
				sej.setDate_fin(rs.getString("date_fin"));
				sej.setNom_patient(rs.getString("nom_patient"));
				sej.setPrenom_patient(rs.getString("prenom_patient"));
				
				listSejour.add(sej);
			}
			
			return listSejour;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<CertificatSejour>();
	}
	
	public List<CertificatSejour> search(String search){
		
		String query = "select c.id_titre, c.date_debut, c.date_fin, \r\n"
				+ "		 	p.nom as nom_patient, p.prenom as prenom_patient\r\n"
				+ "    		from certificat_sejour c\r\n"
				+ "    		join patient p\r\n"
				+ "    		on c.id_patient = p.id_patient "
				+ "			where p.nom like ? or p.prenom like ?";
		
		List<CertificatSejour> listSejour = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatSejour sej = new CertificatSejour();
				sej.setId_titre(rs.getInt("id_titre"));
				sej.setDate_debut(rs.getString("date_debut"));
				sej.setDate_fin(rs.getString("date_fin"));
				sej.setNom_patient(rs.getString("nom_patient"));
				sej.setPrenom_patient(rs.getString("prenom_patient"));
				
				listSejour.add(sej);
			}
			
			return listSejour;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<CertificatSejour>();
	}
	
	public List<CertificatSejour> listSejour(){
		
		String query = "select c.id_titre, c.date_debut, c.date_fin, \r\n"
				+ "		 	p.nom as nom_patient, p.prenom as prenom_patient\r\n"
				+ "    		from certificat_sejour c\r\n"
				+ "    		join patient p\r\n"
				+ "    		on c.id_patient = p.id_patient;";
		
		List<CertificatSejour> listSejour = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatSejour sej = new CertificatSejour();
				sej.setId_titre(rs.getInt("id_titre"));
				sej.setDate_debut(rs.getString("date_debut"));
				sej.setDate_fin(rs.getString("date_fin"));
				sej.setNom_patient(rs.getString("nom_patient"));
				sej.setPrenom_patient(rs.getString("prenom_patient"));
				
				listSejour.add(sej);
			}
			
			return listSejour;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<CertificatSejour>();
	}
	
	public CertificatSejour getById(int id) {
		
		String query = "select c.date_debut, c.date_fin, \r\n"
				+ "		 	p.nom as nom_patient, p.prenom as prenom_patient,\r\n"
				+ "			p.date_naissance as date_naiss_patient\r\n"
				+ "    		from certificat_sejour c\r\n"
				+ "    		join patient p\r\n"
				+ "    		on c.id_patient = p.id_patient\r\n"
				+ "			where c.id_titre = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				CertificatSejour sej = new CertificatSejour();
				sej.setDate_debut(rs.getString("date_debut"));
				sej.setDate_fin(rs.getString("date_fin"));
				sej.setNom_patient(rs.getString("nom_patient"));
				sej.setPrenom_patient(rs.getString("prenom_patient"));
				sej.setDate_naiss_patient(rs.getString("date_naiss_patient"));
				
				return sej;
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

	@Override
	public boolean create(CertificatSejour obj) {
		
		String query = "insert into certificat_sejour(date_debut,date_fin,id_patient) values(?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getDate_debut());
			ps.setString(2, obj.getDate_fin());
			ps.setInt(3, obj.getId_patient());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(CertificatSejour obj) {
		
		String query = "update certificat_sejour set date_debut = ?, date_fin = ?, id_patient = ? where id_titre = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getDate_debut());
			ps.setString(2, obj.getDate_fin());
			ps.setInt(3, obj.getId_patient());
			ps.setInt(4, obj.getId_titre());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(CertificatSejour obj) {
		
		String query = "delete from certificat_sejour where id_titre = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_titre());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
