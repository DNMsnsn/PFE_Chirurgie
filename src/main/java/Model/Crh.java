package Model;

public class Crh {

	private int id_crh;
	private int id_patient;
	private int id_medecin;
	private String nom_patient;
	private String prenom_patient;
	private String nom_medecin;
	private String prenom_medecin;
	private String content;
	private String date_entre;
	private String date_sortie;
	private String date_naiss_patient;
	
	public Crh() {
		
	}
	
	public Crh(int id_crh, int id_patient, int id_medecin, String nom_patient, String prenom_patient,
			String nom_medecin, String prenom_medecin, String content, String date_entre, String date_sortie,
			String date_naiss_patient) {
		
		this.id_crh = id_crh;
		this.id_patient = id_patient;
		this.id_medecin = id_medecin;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.nom_medecin = nom_medecin;
		this.prenom_medecin = prenom_medecin;
		this.content = content;
		this.date_entre = date_entre;
		this.date_sortie = date_sortie;
		this.date_naiss_patient = date_naiss_patient;
	}
	public int getId_crh() {
		return id_crh;
	}
	public void setId_crh(int id_crh) {
		this.id_crh = id_crh;
	}
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public int getId_medecin() {
		return id_medecin;
	}
	public void setId_medecin(int id_medecin) {
		this.id_medecin = id_medecin;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate_entre() {
		return date_entre;
	}
	public void setDate_entre(String date_entre) {
		this.date_entre = date_entre;
	}
	public String getDate_sortie() {
		return date_sortie;
	}
	public void setDate_sortie(String date_sortie) {
		this.date_sortie = date_sortie;
	}
	public String getDate_naiss_patient() {
		return date_naiss_patient;
	}
	public void setDate_naiss_patient(String date_naiss_patient) {
		this.date_naiss_patient = date_naiss_patient;
	}
	
	
}
