package Model;

public class Cro {
	
	private int id_cro;
	private String date_operation;
	private int bloc;
	private String diagnostic_lesionnel;
	private String intervention_paratique;
	private int id_patient;
	private String nom_patient;
	private String prenom_patient;
	private int id_operateur;
	private String nom_operateur;
	private String prenom_operateur;
	private int id_aide;
	private String nom_aide;
	private String prenom_aide;
	private int id_reanimateur;
	private String nom_reanimateur;
	private String prenom_reanimateur;
	private int id_anesthesiste;
	private String nom_anesthesiste;
	private String prenom_anesthesiste;
	private String protocole;
	
	public Cro() {
		
	}
	
	public Cro(int id_cro, String date_operation, int bloc, String diagnostic_lesionnel, String intervention_paratique,
			int id_patient, String nom_patient, String prenom_patient, int id_operateur, String nom_operateur,
			String prenom_operateur, int id_aide, String nom_aide, String prenom_aide, int id_reanimateur,
			String nom_reanimateur, String prenom_reanimateur, int id_anesthesiste, String nom_anesthesiste,
			String prenom_anesthesiste, String protocole) {
		
		this.id_cro = id_cro;
		this.date_operation = date_operation;
		this.bloc = bloc;
		this.diagnostic_lesionnel = diagnostic_lesionnel;
		this.intervention_paratique = intervention_paratique;
		this.id_patient = id_patient;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.id_operateur = id_operateur;
		this.nom_operateur = nom_operateur;
		this.prenom_operateur = prenom_operateur;
		this.id_aide = id_aide;
		this.nom_aide = nom_aide;
		this.prenom_aide = prenom_aide;
		this.id_reanimateur = id_reanimateur;
		this.nom_reanimateur = nom_reanimateur;
		this.prenom_reanimateur = prenom_reanimateur;
		this.id_anesthesiste = id_anesthesiste;
		this.nom_anesthesiste = nom_anesthesiste;
		this.prenom_anesthesiste = prenom_anesthesiste;
		this.protocole = protocole;
	}

	public int getId_cro() {
		return id_cro;
	}

	public void setId_cro(int id_cro) {
		this.id_cro = id_cro;
	}

	public String getDate_operation() {
		return date_operation;
	}

	public void setDate_operation(String date_operation) {
		this.date_operation = date_operation;
	}

	public int getBloc() {
		return bloc;
	}

	public void setBloc(int bloc) {
		this.bloc = bloc;
	}

	public String getDiagnostic_lesionnel() {
		return diagnostic_lesionnel;
	}

	public void setDiagnostic_lesionnel(String diagnostic_lesionnel) {
		this.diagnostic_lesionnel = diagnostic_lesionnel;
	}

	public String getIntervention_paratique() {
		return intervention_paratique;
	}

	public void setIntervention_paratique(String intervention_paratique) {
		this.intervention_paratique = intervention_paratique;
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}

	public String getNom_patient() {
		return nom_patient;
	}

	public void setNom_patient(String nom_patient) {
		this.nom_patient = nom_patient;
	}

	public String getPrenom_patient() {
		return prenom_patient;
	}

	public void setPrenom_patient(String prenom_patient) {
		this.prenom_patient = prenom_patient;
	}

	public int getId_operateur() {
		return id_operateur;
	}

	public void setId_operateur(int id_operateur) {
		this.id_operateur = id_operateur;
	}

	public String getNom_operateur() {
		return nom_operateur;
	}

	public void setNom_operateur(String nom_operateur) {
		this.nom_operateur = nom_operateur;
	}

	public String getPrenom_operateur() {
		return prenom_operateur;
	}

	public void setPrenom_operateur(String prenom_operateur) {
		this.prenom_operateur = prenom_operateur;
	}

	public int getId_aide() {
		return id_aide;
	}

	public void setId_aide(int id_aide) {
		this.id_aide = id_aide;
	}

	public String getNom_aide() {
		return nom_aide;
	}

	public void setNom_aide(String nom_aide) {
		this.nom_aide = nom_aide;
	}

	public String getPrenom_aide() {
		return prenom_aide;
	}

	public void setPrenom_aide(String prenom_aide) {
		this.prenom_aide = prenom_aide;
	}

	public int getId_reanimateur() {
		return id_reanimateur;
	}

	public void setId_reanimateur(int id_reanimateur) {
		this.id_reanimateur = id_reanimateur;
	}

	public String getNom_reanimateur() {
		return nom_reanimateur;
	}

	public void setNom_reanimateur(String nom_reanimateur) {
		this.nom_reanimateur = nom_reanimateur;
	}

	public String getPrenom_reanimateur() {
		return prenom_reanimateur;
	}

	public void setPrenom_reanimateur(String prenom_reanimateur) {
		this.prenom_reanimateur = prenom_reanimateur;
	}

	public int getId_anesthesiste() {
		return id_anesthesiste;
	}

	public void setId_anesthesiste(int id_anesthesiste) {
		this.id_anesthesiste = id_anesthesiste;
	}

	public String getNom_anesthesiste() {
		return nom_anesthesiste;
	}

	public void setNom_anesthesiste(String nom_anesthesiste) {
		this.nom_anesthesiste = nom_anesthesiste;
	}

	public String getPrenom_anesthesiste() {
		return prenom_anesthesiste;
	}

	public void setPrenom_anesthesiste(String prenom_anesthesiste) {
		this.prenom_anesthesiste = prenom_anesthesiste;
	}

	public String getProtocole() {
		return protocole;
	}

	public void setProtocole(String protocole) {
		this.protocole = protocole;
	}
}
