package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDao extends DAO<RendezVous> {

	public RendezVousDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public RendezVous find(int id) {
		
		String query = "select * from rendez_vous where id_rdv = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				RendezVous r = new RendezVous();
				r.setID_rdv(rs.getInt("id_rdv"));
				r.setDt_rdv(rs.getString("date_rdv"));
				r.setID_medecin(rs.getInt("id_medecin"));
				r.setID_patient(rs.getInt("id_patient"));
				
				return r;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new RendezVous();
	}
	
	public List<RendezVous> listById(int id){
		
		String query = "SELECT "
				+ "r.id_rdv, r.date_rdv, r.motif, r.statut, "
				+ "p.nom AS patient_nom, p.prenom AS patient_prenom, "
				+ "m.nom AS medecin_nom, m.prenom AS medecin_prenom "
				+ "FROM rendez_vous r "
				+ "JOIN patient p "
				+ "ON r.id_patient = p.id_patient "
				+ "JOIN medecin m "
				+ "ON r.id_medecin = m.id_medecin "
				+ "where p.id_patient = ?";
		
		List<RendezVous> listRdv = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RendezVous rdv = new RendezVous();
				rdv.setID_rdv(rs.getInt("id_rdv"));
				rdv.setDt_rdv(rs.getString("date_rdv"));
				rdv.setMotif_rdv(rs.getString("motif"));
				rdv.setStatu_rdv(rs.getString("statut"));
				rdv.setFnmPatient(rs.getString("patient_nom"));
				rdv.setSnmPatient(rs.getString("patient_prenom"));
				rdv.setFnmMedecin(rs.getString("medecin_nom"));
				rdv.setSnmMedecin(rs.getString("medecin_prenom"));
				
				listRdv.add(rdv);
			}
			
			return listRdv;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<RendezVous>();
	}
	
	public List<RendezVous> search(String search){
		
		String query = "SELECT "
				+ "r.id_rdv, r.date_rdv, r.motif, r.statut, "
				+ "p.nom AS patient_nom, p.prenom AS patient_prenom, "
				+ "m.nom AS medecin_nom, m.prenom AS medecin_prenom "
				+ "FROM rendez_vous r "
				+ "JOIN patient p "
				+ "ON r.id_patient = p.id_patient "
				+ "JOIN medecin m "
				+ "ON r.id_medecin = m.id_medecin "
				+ "where p.nom like ? or p.prenom like ?";
		
		List<RendezVous> listRdv = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RendezVous rdv = new RendezVous();
				rdv.setID_rdv(rs.getInt("id_rdv"));
				rdv.setDt_rdv(rs.getString("date_rdv"));
				rdv.setMotif_rdv(rs.getString("motif"));
				rdv.setStatu_rdv(rs.getString("statut"));
				rdv.setFnmPatient(rs.getString("patient_nom"));
				rdv.setSnmPatient(rs.getString("patient_prenom"));
				rdv.setFnmMedecin(rs.getString("medecin_nom"));
				rdv.setSnmMedecin(rs.getString("medecin_prenom"));
				
				listRdv.add(rdv);
			}
			
			return listRdv;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<RendezVous>();
	}
	
	public List<RendezVous> listRdv(){
		
		String query = "SELECT "
				+ "r.id_rdv, r.date_rdv, r.motif, r.statut, "
				+ "p.nom AS patient_nom, p.prenom AS patient_prenom, "
				+ "m.nom AS medecin_nom, m.prenom AS medecin_prenom "
				+ "FROM rendez_vous r "
				+ "JOIN patient p "
				+ "ON r.id_patient = p.id_patient "
				+ "JOIN medecin m "
				+ "ON r.id_medecin = m.id_medecin;";
		
		List<RendezVous> listRdv = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RendezVous rdv = new RendezVous();
				rdv.setID_rdv(rs.getInt("id_rdv"));
				rdv.setDt_rdv(rs.getString("date_rdv"));
				rdv.setMotif_rdv(rs.getString("motif"));
				rdv.setStatu_rdv(rs.getString("statut"));
				rdv.setFnmPatient(rs.getString("patient_nom"));
				rdv.setSnmPatient(rs.getString("patient_prenom"));
				rdv.setFnmMedecin(rs.getString("medecin_nom"));
				rdv.setSnmMedecin(rs.getString("medecin_prenom"));
				
				listRdv.add(rdv);
			}
			
			return listRdv;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<RendezVous>();
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
	public boolean create(RendezVous obj) {
		
		String query = "insert into rendez_vous(id_patient,id_medecin,date_rdv,motif,statut)"
				+ "values(?,?,?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_patient());
			ps.setInt(2, obj.getID_medecin());
			ps.setString(3, obj.getDt_rdv());
			ps.setString(4, obj.getMotif_rdv());
			ps.setString(5, obj.getStatu_rdv());
			
			int rs = ps.executeUpdate();
			
			return rs > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(RendezVous obj) {
		
		String query = "update rendez_vous set date_rdv = ?, motif = ?, statut = ?, id_patient = ?, id_medecin = ? where id_rdv = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getDt_rdv());
			ps.setString(2, obj.getMotif_rdv());
			ps.setString(3, obj.getStatu_rdv());
			ps.setInt(4, obj.getID_patient());
			ps.setInt(5, obj.getID_medecin());
			ps.setInt(6, obj.getID_rdv());
			
			int rs = ps.executeUpdate();
			
			return rs > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean delete(RendezVous obj) {
		
		return false;
	}
	
	public boolean deleteRdv(int id) {
		
		String query = "delete from rendez_vous where id_rdv = ?";
		
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
