package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertificatMedicalDao extends DAO<CertificatMedical> {

	public CertificatMedicalDao(Connection cnx) {
		super(cnx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CertificatMedical find(int id) {
		
		String query = "select * from certificat_medical where id_certificat = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				CertificatMedical c = new CertificatMedical();
				c.setId_certificat(rs.getInt("id_certificat"));
				c.setId_medecin(rs.getInt("id_medecin"));
				c.setId_patient(rs.getInt("id_patient"));
				c.setDate_emission(rs.getString("date_emission"));
				
				return c;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new CertificatMedical();
	}
	
	public List<CertificatMedical> listById(int id){
		
		String query = "select c.id_certificat, c.statut, c.date_emission,\r\n"
				+ "		 p.nom as nom_patient, p.prenom as prenom_patient,\r\n"
				+ "		 m.nom as nom_medecin, m.prenom as prenom_medecin\r\n"
				+ "		 from certificat_medical c\r\n"
				+ "		 join patient p on c.id_patient = p.id_patient\r\n"
				+ "		 join medecin m on c.id_medecin = m.id_medecin "
				+ "		 where p.id_patient = ?";
		
		List<CertificatMedical> listCertificat = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatMedical cert = new CertificatMedical();
				cert.setId_certificat(rs.getInt("id_certificat"));
				cert.setStatut(rs.getString("statut"));
				cert.setDate_emission(rs.getString("date_emission"));
				cert.setNom_patient(rs.getString("nom_patient"));
				cert.setPrenom_patient(rs.getString("prenom_patient"));
				cert.setNom_medecin(rs.getString("nom_medecin"));
				cert.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				
				listCertificat.add(cert);
			}
			
			return listCertificat;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<CertificatMedical>();
	}
	
	public List<CertificatMedical> search(String search){
		
		String query = "select c.id_certificat, c.statut, c.date_emission,\r\n"
				+ "		 p.nom as nom_patient, p.prenom as prenom_patient,\r\n"
				+ "		 m.nom as nom_medecin, m.prenom as prenom_medecin\r\n"
				+ "		 from certificat_medical c\r\n"
				+ "		 join patient p on c.id_patient = p.id_patient\r\n"
				+ "		 join medecin m on c.id_medecin = m.id_medecin "
				+ "		 where p.nom like ? or p.prenom like ?";
		
		List<CertificatMedical> listCertificat = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatMedical cert = new CertificatMedical();
				cert.setId_certificat(rs.getInt("id_certificat"));
				cert.setStatut(rs.getString("statut"));
				cert.setDate_emission(rs.getString("date_emission"));
				cert.setNom_patient(rs.getString("nom_patient"));
				cert.setPrenom_patient(rs.getString("prenom_patient"));
				cert.setNom_medecin(rs.getString("nom_medecin"));
				cert.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				
				listCertificat.add(cert);
			}
			
			return listCertificat;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<CertificatMedical>();
	}

	public List<CertificatMedical> listCertificat(){
		
		String query = "select c.id_certificat, c.statut, c.date_emission,\r\n"
				+ "		 p.nom as nom_patient, p.prenom as prenom_patient,\r\n"
				+ "		 m.nom as nom_medecin, m.prenom as prenom_medecin\r\n"
				+ "		 from certificat_medical c\r\n"
				+ "		 join patient p on c.id_patient = p.id_patient\r\n"
				+ "		 join medecin m on c.id_medecin = m.id_medecin; ";
		
		List<CertificatMedical> listCertificat = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CertificatMedical cert = new CertificatMedical();
				cert.setId_certificat(rs.getInt("id_certificat"));
				cert.setStatut(rs.getString("statut"));
				cert.setDate_emission(rs.getString("date_emission"));
				cert.setNom_patient(rs.getString("nom_patient"));
				cert.setPrenom_patient(rs.getString("prenom_patient"));
				cert.setNom_medecin(rs.getString("nom_medecin"));
				cert.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				
				listCertificat.add(cert);
			}
			
			return listCertificat;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public CertificatMedical getById(int id) {
		
		String query = "select c.statut, c.date_emission,\r\n"
				+ "		 p.nom as nom_patient, p.prenom as prenom_patient, p.date_naissance as date_naiss_patient,\r\n"
				+ "		 m.nom as nom_medecin, m.prenom as prenom_medecin\r\n"
				+ "		 from certificat_medical c\r\n"
				+ "		 join patient p on c.id_patient = p.id_patient\r\n"
				+ "		 join medecin m on c.id_medecin = m.id_medecin"
				+ "		 where c.id_certificat = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				CertificatMedical cert = new CertificatMedical();
				cert.setStatut(rs.getString("statut"));
				cert.setDate_emission(rs.getString("date_emission"));
				cert.setNom_patient(rs.getString("nom_patient"));
				cert.setPrenom_patient(rs.getString("prenom_patient"));
				cert.setDate_naiss_patient(rs.getString("date_naiss_patient"));
				cert.setNom_medecin(rs.getString("nom_medecin"));
				cert.setPrenom_medecin(rs.getString("prenom_medecin"));
				
				return cert;
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
	public boolean create(CertificatMedical obj) {
		
		String query = "insert into certificat_medical(statut,id_patient,id_medecin)"
				+ "values(?,?,?);";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getStatut());
			ps.setInt(2, obj.getId_patient());
			ps.setInt(3, obj.getId_medecin());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public boolean update(CertificatMedical obj) {
		
		String query = "update certificat_medical set statut = ?, id_patient = ?, id_medecin = ? where id_certificat = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getStatut());
			ps.setInt(2, obj.getId_patient());
			ps.setInt(3, obj.getId_medecin());
			ps.setInt(4, obj.getId_certificat());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(CertificatMedical obj) {
		
		String query = "delete from certificat_medical where id_certificat = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getId_certificat());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
