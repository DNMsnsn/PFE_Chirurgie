package Model;


public class ArretTravail {

	private int id_arret;
	private String type;
	private String date_debut;
	private String date_fin;
	private int id_patient;
	private String nom_patient;
	private String prenom_patient;
	private String date_naiss_patient;
	private int id_medecin;
	private String nom_medecin;
	private String prenom_medecin;
	
	public ArretTravail() {
		
	}
	
	public ArretTravail(int id_arret, String type, String date_debut, String date_fin, int id_patient, String nom_patient,
			String prenom_patient, int id_medecin, String nom_medecin, String prenom_medecin, String date_naiss_patient) {
		
		this.id_arret = id_arret;
		this.type = type;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.id_patient = id_patient;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.id_medecin = id_medecin;
		this.nom_medecin = nom_medecin;
		this.prenom_medecin = prenom_medecin;
		this.date_naiss_patient = date_naiss_patient;
	}

	public int getId_arret() {
		return id_arret;
	}

	public void setId_arret(int id_arret) {
		this.id_arret = id_arret;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}

	public String getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
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
