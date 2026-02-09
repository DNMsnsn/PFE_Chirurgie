package Model;

import java.sql.Connection;

public class QrCodeMedecinDao extends DAO<QrCodeMedecin> {

	public QrCodeMedecinDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public QrCodeMedecin find(int id) {
		
		return null;
	}

	@Override
	public boolean create(QrCodeMedecin obj) {
		
		return false;
	}

	@Override
	public boolean update(QrCodeMedecin obj) {
		
		return false;
	}

	@Override
	public boolean delete(QrCodeMedecin obj) {
		
		return false;
	}

}
