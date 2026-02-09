package Model;

import java.sql.Date;

public class QrCodePatient {

	private int ID_qrCode;
	private String code_unique;
	private Date dt_generation;
	private boolean actif;
	private int id_patient;
	
	public QrCodePatient() {
		
	}

	public QrCodePatient(int iD_qrCode, String code_unique, Date dt_generation, boolean actif, int id_patient) {
		
		ID_qrCode = iD_qrCode;
		this.code_unique = code_unique;
		this.dt_generation = dt_generation;
		this.actif = actif;
		this.id_patient = id_patient;
	}

	public int getID_qrCode() {
		return ID_qrCode;
	}

	public void setID_qrCode(int iD_qrCode) {
		ID_qrCode = iD_qrCode;
	}

	public String getCode_unique() {
		return code_unique;
	}

	public void setCode_unique(String code_unique) {
		this.code_unique = code_unique;
	}

	public Date getDt_generation() {
		return dt_generation;
	}

	public void setDt_generation(Date dt_generation) {
		this.dt_generation = dt_generation;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	
}
