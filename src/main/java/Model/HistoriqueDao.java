package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDao extends DAO<Historique> {

	public HistoriqueDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public Historique find(int id) {
		
		return null;
	}

	@Override
	public boolean create(Historique obj) {
		
		String query = "insert into historique(type_action,details,id_patient,id_amsp) "
				+ "values(?,?,?,?)";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getType_histo());
			ps.setString(2, obj.getDetails());
			
			if (obj.getID_patient() == null) {
			    ps.setNull(3, java.sql.Types.INTEGER);
			} else {
			    ps.setInt(3, obj.getID_patient());
			}

			if (obj.getID_amsp() == null) {
			    ps.setNull(4, java.sql.Types.INTEGER);
			} else {
			    ps.setInt(4, obj.getID_amsp());
			}
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Historique> listHis(){
		
		String query = "select * from historique";
		
		List<Historique> listHisto = new ArrayList<>();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Historique h = new Historique();
				h.setID_histo(rs.getInt("id_historique"));
				h.setID_patient(rs.getInt("id_patient"));
				h.setID_amsp(rs.getInt("id_amsp"));
				h.setType_histo(rs.getString("type_action"));
				h.setDetails(rs.getString("details"));
				h.setDt_action(rs.getString("date_action"));
				
				listHisto.add(h);
			}
			
			return listHisto;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(Historique obj) {
		
		return false;
	}

	@Override
	public boolean delete(Historique obj) {
		
		String query = "delete from historique where id_historique = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_histo());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteAll() {
		
		String query = "delete from historique";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
