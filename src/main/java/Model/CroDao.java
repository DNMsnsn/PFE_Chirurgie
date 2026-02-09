package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CroDao extends DAO<Cro> {

	public CroDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public Cro find(int id) {
		
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
	
	public int idOperateur(String fnm, String snm) {
		
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
	
	public int idAide(String fnm, String snm) {
		
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
	
	public int idReanimateur(String fnm, String snm) {
		
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

	public int idAnesthesiste(String fnm, String snm) {
	
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
	
	public List<Cro> getById(int id){
		
		String query = "SELECT\r\n"
				+ "    c.id_cro,\r\n"
				+ "    c.date_operation,\r\n"
				+ "    c.bloc,\r\n"
				+ "    c.diagnostic_lesionnel,\r\n"
				+ "    c.intervention_pratique,\r\n"
				+ "    c.protocole,\r\n"
				+ "    c.id_patient,\r\n"
				+ "    c.id_operateur,\r\n"
				+ "    c.id_aide,\r\n"
				+ "    c.id_reanimateur,\r\n"
				+ "    c.id_anesthesiste,\r\n"
				+ "\r\n"
				+ "    p.nom    AS nom_patient,\r\n"
				+ "    p.prenom AS prenom_patient,\r\n"
				+ "\r\n"
				+ "    o.nom    AS nom_operateur,\r\n"
				+ "    o.prenom AS prenom_operateur,\r\n"
				+ "\r\n"
				+ "    a.nom    AS nom_aide,\r\n"
				+ "    a.prenom AS prenom_aide,\r\n"
				+ "\r\n"
				+ "    r.nom    AS nom_reanimateur,\r\n"
				+ "    r.prenom AS prenom_reanimateur,\r\n"
				+ "\r\n"
				+ "    an.nom    AS nom_anesthesiste,\r\n"
				+ "    an.prenom AS prenom_anesthesiste\r\n"
				+ "\r\n"
				+ "FROM cro c\r\n"
				+ "\r\n"
				+ "JOIN patient p\r\n"
				+ "    ON c.id_patient = p.id_patient\r\n"
				+ "\r\n"
				+ "JOIN medecin o\r\n"
				+ "    ON c.id_operateur = o.id_medecin\r\n"
				+ "\r\n"
				+ "LEFT JOIN medecin a\r\n"
				+ "    ON c.id_aide = a.id_medecin\r\n"
				+ "\r\n"
				+ "LEFT JOIN medecin r\r\n"
				+ "    ON c.id_reanimateur = r.id_medecin\r\n"
				+ "\r\n"
				+ "LEFT JOIN medecin an\r\n"
				+ "    ON c.id_anesthesiste = an.id_medecin\r\n"
				+ "\r\n"
				+ "WHERE c.id_patient = ?;\r\n"
				+ "";
				
		List<Cro> l = new ArrayList<>();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cro c = new Cro();
				c.setId_cro(rs.getInt("id_cro"));
				c.setDate_operation(rs.getString("date_operation"));
				c.setBloc(rs.getInt("bloc"));
				c.setDiagnostic_lesionnel(rs.getString("diagnostic_lesionnel"));
				c.setIntervention_paratique(rs.getString("intervention_pratique"));
				c.setProtocole(rs.getString("protocole"));
				
				c.setId_patient(rs.getInt("id_patient"));
				c.setNom_patient(rs.getString("nom_patient"));
				c.setPrenom_patient(rs.getString("prenom_patient"));
				
				c.setId_operateur(rs.getInt("id_operateur"));
				c.setNom_operateur(rs.getString("nom_operateur"));
				c.setPrenom_operateur(rs.getString("prenom_operateur"));
				
				c.setId_aide(rs.getInt("id_aide"));
				c.setNom_aide(rs.getString("nom_aide"));
				c.setPrenom_aide(rs.getString("prenom_aide"));
				
				c.setId_reanimateur(rs.getInt("id_reanimateur"));
				c.setNom_reanimateur(rs.getString("nom_reanimateur"));
				c.setPrenom_reanimateur(rs.getString("prenom_reanimateur"));
				
				c.setId_anesthesiste(rs.getInt("id_anesthesiste"));
				c.setNom_anesthesiste(rs.getString("nom_anesthesiste"));
				c.setPrenom_anesthesiste(rs.getString("prenom_anesthesiste"));
				
				l.add(c);
			}
			
			return l;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Cro>();
	}
	
	public List<Cro> listCro(){
		
		String query = "SELECT \r\n"
				+ "    c.id_cro, \r\n"
				+ "    c.date_operation, \r\n"
				+ "    c.bloc, \r\n"
				+ "    c.diagnostic_lesionnel, \r\n"
				+ "    c.intervention_pratique,\r\n"
				+ "    c.id_patient, \r\n"
				+ "    c.id_operateur, \r\n"
				+ "    c.id_aide, \r\n"
				+ "    c.id_reanimateur, \r\n"
				+ "    c.id_anesthesiste, \r\n"
				+ "    c.protocole,\r\n"
				+ "    p.nom AS nom_patient, \r\n"
				+ "    p.prenom AS prenom_patient,\r\n"
				+ "    o.nom AS nom_operateur, \r\n"
				+ "    o.prenom AS prenom_operateur,\r\n"
				+ "    a.nom AS nom_aide, \r\n"
				+ "    a.prenom AS prenom_aide,\r\n"
				+ "    r.nom AS nom_reanimateur, \r\n"
				+ "    r.prenom AS prenom_reanimateur,\r\n"
				+ "    an.nom AS nom_anesthesiste, \r\n"
				+ "    an.prenom AS prenom_anesthesiste\r\n"
				+ "FROM cro c\r\n"
				+ "JOIN patient p ON c.id_patient = p.id_patient\r\n"
				+ "JOIN medecin o ON c.id_operateur = o.id_medecin\r\n"
				+ "JOIN medecin a ON c.id_aide = a.id_medecin\r\n"
				+ "JOIN medecin r ON c.id_reanimateur = r.id_medecin\r\n"
				+ "JOIN medecin an ON c.id_anesthesiste = an.id_medecin;\r\n"
				+ "";
		
		List<Cro> l = new ArrayList<>();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cro c = new Cro();
				c.setId_cro(rs.getInt("id_cro"));
				c.setDate_operation(rs.getString("date_operation"));
				c.setBloc(rs.getInt("bloc"));
				c.setDiagnostic_lesionnel(rs.getString("diagnostic_lesionnel"));
				c.setIntervention_paratique(rs.getString("intervention_pratique"));
				c.setProtocole(rs.getString("protocole"));
				
				c.setId_patient(rs.getInt("id_patient"));
				c.setNom_patient(rs.getString("nom_patient"));
				c.setPrenom_patient(rs.getString("prenom_patient"));
				
				c.setId_operateur(rs.getInt("id_operateur"));
				c.setNom_operateur(rs.getString("nom_operateur"));
				c.setPrenom_operateur(rs.getString("prenom_operateur"));
				
				c.setId_aide(rs.getInt("id_aide"));
				c.setNom_aide(rs.getString("nom_aide"));
				c.setPrenom_aide(rs.getString("prenom_aide"));
				
				c.setId_reanimateur(rs.getInt("id_reanimateur"));
				c.setNom_reanimateur(rs.getString("nom_reanimateur"));
				c.setPrenom_reanimateur(rs.getString("prenom_reanimateur"));
				
				c.setId_anesthesiste(rs.getInt("id_anesthesiste"));
				c.setNom_anesthesiste(rs.getString("nom_anesthesiste"));
				c.setPrenom_anesthesiste(rs.getString("prenom_anesthesiste"));
				
				l.add(c);
			}
			
			return l;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Cro>();
	}

	@Override
	public boolean create(Cro obj) {
		
		String query = "insert into cro(date_operation,bloc,diagnostic_lesionnel,intervention_pratique,protocole,"
				+ "id_patient,id_operateur,id_aide,id_reanimateur,id_anesthesiste) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1,obj.getDate_operation());
			ps.setInt(2,obj.getBloc());
			ps.setString(3,obj.getDiagnostic_lesionnel());
			ps.setString(4,obj.getIntervention_paratique());
			ps.setString(5,obj.getProtocole());
			
			ps.setInt(6, obj.getId_patient());
			ps.setInt(7, obj.getId_operateur());
			ps.setInt(8, obj.getId_aide());
			ps.setInt(9, obj.getId_reanimateur());
			ps.setInt(10, obj.getId_anesthesiste());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Cro obj) {
		
		String query = "update cro set date_operation = ?, bloc = ? , diagnostic_lesionnel = ? , intervention_pratique = ? , protocole = ? , "
				+ "id_patient = ? , id_operateur = ? , id_aide = ? , id_reanimateur = ? , id_anesthesiste = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1,obj.getDate_operation());
			ps.setInt(2,obj.getBloc());
			ps.setString(3,obj.getDiagnostic_lesionnel());
			ps.setString(4,obj.getIntervention_paratique());
			ps.setString(5,obj.getProtocole());
			
			ps.setInt(6, obj.getId_patient());
			ps.setInt(7, obj.getId_operateur());
			ps.setInt(8, obj.getId_aide());
			ps.setInt(9, obj.getId_reanimateur());
			ps.setInt(10, obj.getId_anesthesiste());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Cro obj) {
		
		String query = "delete from cro where id_cro = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1,obj.getId_cro());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
