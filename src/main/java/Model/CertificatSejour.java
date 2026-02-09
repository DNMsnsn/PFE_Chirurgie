package Model;

public class CertificatSejour {

	private int id_titre;
	private String date_debut;
	private String date_fin;
	private int id_patient;
	private String nom_patient;
	private String prenom_patient;
	private String date_naiss_patient;
	
	public CertificatSejour() {
		
	}
	
	public CertificatSejour(int id_titre, String date_debut, String date_fin, int id_patient, String nom_patient,
			String prenom_patient, String date_naiss_patient) {
		
		this.id_titre = id_titre;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.id_patient = id_patient;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.date_naiss_patient = date_naiss_patient;
	}

	public int getId_titre() {
		return id_titre;
	}

	public void setId_titre(int id_titre) {
		this.id_titre = id_titre;
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

	public String getDate_naiss_patient() {
		return date_naiss_patient;
	}

	public void setDate_naiss_patient(String date_naiss_patient) {
		this.date_naiss_patient = date_naiss_patient;
	}
	
	
	
}
