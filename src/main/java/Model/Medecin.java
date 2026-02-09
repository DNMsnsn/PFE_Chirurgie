package Model;

public class Medecin {
	
	private int ID_medecin;
	private String nom_medecin;
	private String prenom_medecin;
	private String tel_medecin;
	private String email_medecin;
	private String specialite_medecin;
	private String role_medecin;
	
	public Medecin() {
		
	}
	
	public Medecin(int iD_medecin, String nom_medecin, String prenom_medecin, String tel_medecin, String email_medecin,
			String specialite_medecin, String role_medecin) {
		ID_medecin = iD_medecin;
		this.nom_medecin = nom_medecin;
		this.prenom_medecin = prenom_medecin;
		this.tel_medecin = tel_medecin;
		this.email_medecin = email_medecin;
		this.specialite_medecin = specialite_medecin;
		this.role_medecin = role_medecin;
	}

	public int getID_medecin() {
		return ID_medecin;
	}

	public void setID_medecin(int iD_medecin) {
		ID_medecin = iD_medecin;
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

	public String getTel_medecin() {
		return tel_medecin;
	}

	public void setTel_medecin(String tel_medecin) {
		this.tel_medecin = tel_medecin;
	}

	public String getEmail_medecin() {
		return email_medecin;
	}

	public void setEmail_medecin(String email_medecin) {
		this.email_medecin = email_medecin;
	}

	public String getSpecialite_medecin() {
		return specialite_medecin;
	}

	public void setSpecialite_medecin(String specialite_medecin) {
		this.specialite_medecin = specialite_medecin;
	}

	public String getRole_medecin() {
		return role_medecin;
	}

	public void setRole_medecin(String role_medecin) {
		this.role_medecin = role_medecin;
	}
	
	

}
