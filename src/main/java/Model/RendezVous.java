package Model;

public class RendezVous {

	private int ID_rdv;
	private String dt_rdv;
	private String motif_rdv;
	private String statu_rdv;
	private int ID_patient;
	private int ID_medecin;
	private String fnmPatient;
	private String snmPatient;
	private String fnmMedecin;
	private String snmMedecin;

	public RendezVous() {
		
	}

	public RendezVous(int iD_rdv, String dt_rdv, String motif_rdv, String statu_rdv, int iD_patient,
			int iD_medecin, String fnmPatient, String snmPatient, String fnmMedecin, String snmMedecin) {
		this.ID_rdv = iD_rdv;
		this.dt_rdv = dt_rdv;
		this.motif_rdv = motif_rdv;
		this.statu_rdv = statu_rdv;
		this.ID_patient = iD_patient;
		this.ID_medecin = iD_medecin;
		this.fnmPatient = fnmPatient;
		this.snmPatient = snmPatient;
		this.fnmMedecin = fnmMedecin;
		this.snmMedecin = snmMedecin;
	}

	public int getID_rdv() {
		return ID_rdv;
	}

	public void setID_rdv(int iD_rdv) {
		ID_rdv = iD_rdv;
	}

	public String getDt_rdv() {
		return dt_rdv;
	}

	public void setDt_rdv(String dt_rdv) {
		this.dt_rdv = dt_rdv;
	}

	public String getMotif_rdv() {
		return motif_rdv;
	}

	public void setMotif_rdv(String motif_rdv) {
		this.motif_rdv = motif_rdv;
	}

	public String getStatu_rdv() {
		return statu_rdv;
	}

	public void setStatu_rdv(String statu_rdv) {
		this.statu_rdv = statu_rdv;
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
