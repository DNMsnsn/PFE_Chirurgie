package Model;

import java.sql.SQLException;

public class UnitTesting {

	public static void main(String[] args) {
		
		// addPatient();
		// addAmsp();
		// addMedecin();
		
		ArretTravail arr = new ArretTravail();
		arr.setType("UN ARRET DE TRAVAIL");
		arr.setDate_debut("2026-02-02");
		arr.setDate_fin("2026-03-03");
		arr.setId_patient(1);
		arr.setId_medecin(2);
		
		try {
			ArretTravailDao dao = new ArretTravailDao(GetConnected.getConnected());
			if(dao.create(arr)) {
				System.out.println("Ajouter");
			} else {
				System.out.println("Pas Ajouter !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addPatient() {
		try {
			
			Patient patient = new Patient();
			patient.setNom_patient("Kassouri");
			patient.setPrenom_patient("Tahar");
			//patient.setDt_naiss_patient(Date.valueOf("2002-02-27"));
			patient.setSexe_patient("homme");
			patient.setTel_patient("0698764278");
			patient.setEmail_patient("msnsn@gamil.com");
			patient.setAdresse_patient("Idjeur");
			patient.setGrp_sang_patient("O+");
			
			DAO<Patient> dao = new PatientDao(GetConnected.getConnected());
			
			if(dao.create(patient)) {
				System.out.println("Patient ajouter !");
			} else {
				System.out.println("Patient non ajouter !");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void addAmsp() {
		try {
			Amsp am = new Amsp();
			am.setNom_amsp("Kassouri");
			am.setPrenom_amsp("Tahar");
			am.setEmail_amsp("kssr@chu.com");
			am.setTel_amsp("0987650321");
			am.setFonction_amsp("AMSP - Chirurgie");
			
			DAO<Amsp> doa = new AmspDao(GetConnected.getConnected());
			
			if(doa.create(am)) {
				System.out.println("Assistant Ajouter !");
			} else {
				System.out.println("Assistant non ajouter !");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void addMedecin() {
		
		try {
			Medecin med = new Medecin();
			med.setNom_medecin("Kassouri");
			med.setPrenom_medecin("Tahar");
			med.setSpecialite_medecin("Neurologie");
			med.setTel_medecin("0956453423");
			med.setEmail_medecin("med@hos.com");
			med.setRole_medecin("chirurgie");
			
			DAO<Medecin> dao = new MedecinDao(GetConnected.getConnected());
			
			if(dao.create(med)) {
				System.out.println("Medecin Ajouter");
			} else {
				System.out.println("Medecin non ajouter !");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
