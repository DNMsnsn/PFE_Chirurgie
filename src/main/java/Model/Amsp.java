package Model;

public class Amsp {
	private int ID_amsp;
	private String nom_amsp;
	private String prenom_amsp;
	private String email_amsp;
	private String tel_amsp;
	private String password;
	private String fonction_amsp;
	
	public Amsp() {
		
	}
	
	public Amsp(int iD_amsp, String nom_amsp, String prenom_amsp, String email_amsp, String tel_amsp,
			String fonction_amsp, String password) {
		ID_amsp = iD_amsp;
		this.nom_amsp = nom_amsp;
		this.prenom_amsp = prenom_amsp;
		this.email_amsp = email_amsp;
		this.tel_amsp = tel_amsp;
		this.fonction_amsp = fonction_amsp;
		this.setPassword(password);
	}

	public int getID_amsp() {
		return ID_amsp;
	}

	public void setID_amsp(int iD_amsp) {
		ID_amsp = iD_amsp;
	}

	public String getNom_amsp() {
		return nom_amsp;
	}

	public void setNom_amsp(String nom_amsp) {
		this.nom_amsp = nom_amsp;
	}

	public String getPrenom_amsp() {
		return prenom_amsp;
	}

	public void setPrenom_amsp(String prenom_amsp) {
		this.prenom_amsp = prenom_amsp;
	}

	public String getEmail_amsp() {
		return email_amsp;
	}

	public void setEmail_amsp(String email_amsp) {
		this.email_amsp = email_amsp;
	}

	public String getTel_amsp() {
		return tel_amsp;
	}

	public void setTel_amsp(String tel_amsp) {
		this.tel_amsp = tel_amsp;
	}

	public String getFonction_amsp() {
		return fonction_amsp;
	}

	public void setFonction_amsp(String fonction_amsp) {
		this.fonction_amsp = fonction_amsp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
