package Model;


public class CertificatMedical {
	
	private int id_certificat;
	private String statut;
	private String date_emission;
	private int id_patient;
	private String nom_patient;
	private String prenom_patient;
	private String date_naiss_patient;
	private int id_medecin;
	private String nom_medecin;
	private String prenom_medecin;
	
	public CertificatMedical() {
		
	}
	
	public CertificatMedical(int id_certificat, String statut, String date_emission, int id_patient, String nom_patient,
			String prenom_patient, String date_naiss_patient, int id_medecin, String nom_medecin, String prenom_medecin) {
		
		this.id_certificat = id_certificat;
		this.statut = statut;
		this.date_emission = date_emission;
		this.id_patient = id_patient;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.id_medecin = id_medecin;
		this.nom_medecin = nom_medecin;
		this.prenom_medecin = prenom_medecin;
		this.date_naiss_patient = date_naiss_patient; 
	}

	public int getId_certificat() {
		return id_certificat;
	}

	public void setId_certificat(int id_certificat) {
		this.id_certificat = id_certificat;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getDate_emission() {
		return date_emission;
	}

	public void setDate_emission(String date_emission) {
		this.date_emission = date_emission;
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

	public int getId_medecin() {
		return id_medecin;
	}

	public void setId_medecin(int id_medecin) {
		this.id_medecin = id_medecin;
	}

	public String getNom_medecin() {
		return nom_medecin;
	}

	public void setNom_medecin(String nom_medecin) {
		this.nom_medecin = nom_medecin;
	}

	public String getPrenom_medecin() {
		return prenom_medecin;
	}

	public void setPrenom_medecin(String prenom_medecin) {
		this.prenom_medecin = prenom_medecin;
	}

	public String getDate_naiss_patient() {
		return date_naiss_patient;
	}

	public void setDate_naiss_patient(String date_naiss_patient) {
		this.date_naiss_patient = date_naiss_patient;
	}
	
	

}
