package Model;

public class Diagnostic {

	private int ID_diagnostic;
	private String description_diag;
	private String dt_diag;
	private int ID_patient;
	private int ID_medecin;
	private String fnmPatient;
	private String snmPatient;
	private String fnmMedecin;
	private String snmMedecin;
	
	public Diagnostic() {
		
	}

	public Diagnostic(int iD_diagnostic, String description_diag, String dt_diag, int iD_patient, int iD_medecin,
			String fnmPatient, String snmPatient, String fnmMedecin, String snmMedecin) {
		ID_diagnostic = iD_diagnostic;
		this.description_diag = description_diag;
		this.dt_diag = dt_diag;
		ID_patient = iD_patient;
		ID_medecin = iD_medecin;
		this.fnmPatient = fnmPatient;
		this.snmPatient = snmPatient;
		this.fnmMedecin = fnmMedecin;
		this.snmMedecin = snmMedecin;
	}

	public int getID_diagnostic() {
		return ID_diagnostic;
	}

	public void setID_diagnostic(int iD_diagnostic) {
		ID_diagnostic = iD_diagnostic;
	}

	public String getDescription_diag() {
		return description_diag;
	}

	public void setDescription_diag(String description_diag) {
		this.description_diag = description_diag;
	}

	public String getDt_diag() {
		return dt_diag;
	}

	public void setDt_diag(String dt_diag) {
		this.dt_diag = dt_diag;
	}

	public int getID_patient() {
		return ID_patient;
	}

	public void setID_patient(int iD_patient) {
		ID_patient = iD_patient;
	}

	public int getID_medecin() {
		return ID_medecin;
	}

	public void setID_medecin(int iD_medecin) {
		ID_medecin = iD_medecin;
	}
	
	public String getFnmPatient() {
		return fnmPatient;
	}

	public void setFnmPatient(String fnmPatient) {
		this.fnmPatient = fnmPatient;
	}

	public String getSnmPatient() {
		return snmPatient;
	}

	public void setSnmPatient(String snmPatient) {
		this.snmPatient = snmPatient;
	}

	public String getFnmMedecin() {
		return fnmMedecin;
	}

	public void setFnmMedecin(String fnmMedecin) {
		this.fnmMedecin = fnmMedecin;
	}

	public String getSnmMedecin() {
		return snmMedecin;
	}

	public void setSnmMedecin(String snmMedecin) {
		this.snmMedecin = snmMedecin;
	}
	
}
