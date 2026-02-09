package Model;

public class Historique {

	private int ID_histo;
	private String type_histo;
	private String details;
	private String dt_action;
	private Integer ID_patient;
	private Integer ID_amsp;
	
	public Historique() {
		
	}

	public Historique(int iD_histo, String type_histo, String details, String dt_action, Integer iD_patient, Integer iD_amsp) {
		
		ID_histo = iD_histo;
		this.type_histo = type_histo;
		this.details = details;
		this.dt_action = dt_action;
		ID_patient = iD_patient;
		ID_amsp = iD_amsp;
	}

	public int getID_histo() {
		return ID_histo;
	}

	public void setID_histo(int iD_histo) {
		ID_histo = iD_histo;
	}

	public String getType_histo() {
		return type_histo;
	}

	public void setType_histo(String type_histo) {
		this.type_histo = type_histo;
	}

	public String getDt_action() {
		return dt_action;
	}

	public void setDt_action(String dt_action) {
		this.dt_action = dt_action;
	}

	public Integer getID_patient() {
		return ID_patient;
	}

	public void setID_patient(Integer iD_patient) {
		ID_patient = iD_patient;
	}

	public Integer getID_amsp() {
		return ID_amsp;
	}

	public void setID_amsp(Integer iD_amsp) {
		ID_amsp = iD_amsp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
