package Model;

import java.sql.Date;

public class Patient {

	private int ID_patient;
	private String nom_patient;
	private String prenom_patient;
	private String dt_naiss_patient;
	private /*Sexe*/ String sexe_patient;
	private String tel_patient;
	private String email_patient;
	private String adresse_patient;
	private String grp_sang_patient;
	private Date dt_creation_patient;
	
	public Patient() {
		
	}

	public Patient(int iD_patient, String nom_patient, String prenom_patient, String dt_naiss_patient,
			String sexe_patient, String tel_patient, String email_patient, String adresse_patient,
			String grp_sang_patient, Date dt_creation_patient) {
		ID_patient = iD_patient;
		this.nom_patient = nom_patient;
		this.prenom_patient = prenom_patient;
		this.dt_naiss_patient = dt_naiss_patient;
		this.sexe_patient = sexe_patient;
		this.tel_patient = tel_patient;
		this.email_patient = email_patient;
		this.adresse_patient = adresse_patient;
		this.grp_sang_patient = grp_sang_patient;
		this.dt_creation_patient = dt_creation_patient;
	}

	public int getID_patient() {
		return ID_patient;
	}

	public void setID_patient(int iD_patient) {
		ID_patient = iD_patient;
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

	public String getDt_naiss_patient() {
		return dt_naiss_patient;
	}

	public void setDt_naiss_patient(String dt_naiss_patient) {
		this.dt_naiss_patient = dt_naiss_patient;
	}

	public String getSexe_patient() {
		return sexe_patient;
	}

	public void setSexe_patient(String sexe_patient) {
		this.sexe_patient = sexe_patient;
	}

	public String getTel_patient() {
		return tel_patient;
	}

	public void setTel_patient(String tel_patient) {
		this.tel_patient = tel_patient;
	}

	public String getEmail_patient() {
		return email_patient;
	}

	public void setEmail_patient(String email_patient) {
		this.email_patient = email_patient;
	}

	public String getAdresse_patient() {
		return adresse_patient;
	}

	public void setAdresse_patient(String adresse_patient) {
		this.adresse_patient = adresse_patient;
	}

	public String getGrp_sang_patient() {
		return grp_sang_patient;
	}

	public void setGrp_sang_patient(String grp_sang_patient) {
		this.grp_sang_patient = grp_sang_patient;
	}

	public Date getDt_creation_patient() {
		return dt_creation_patient;
	}

	public void setDt_creation_patient(Date dt_creation_patient) {
		this.dt_creation_patient = dt_creation_patient;
	}
	
}
