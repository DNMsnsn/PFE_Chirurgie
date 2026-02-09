package Model;

import java.sql.Connection;

public class QrCodePatientDao extends DAO<QrCodePatient> {

	public QrCodePatientDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public QrCodePatient find(int id) {
		
		return null;
	}

	@Override
	public boolean create(QrCodePatient obj) {
		
		return false;
	}

	@Override
	public boolean update(QrCodePatient obj) {
		
		return false;
	}

	@Override
	public boolean delete(QrCodePatient obj) {
		
		return false;
	}

}
