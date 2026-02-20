package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmspDao extends DAO<Amsp> {

	public AmspDao(Connection cnx) {
		super(cnx);
		
	}

	@Override
	public Amsp find(int id) {
		
		String query = "select * from amsp where id_amsp = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Amsp a = new Amsp();
				a.setID_amsp(rs.getInt("id_amsp"));
				a.setNom_amsp(rs.getString("nom"));
				a.setPrenom_amsp(rs.getString("prenom"));
				
				return a;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Amsp();
	}
	
	public List<Amsp> search(String search) {
		
		List<Amsp> list = new ArrayList<>(); 
		
		String query = "select * from amsp where nom like ? or prenom like ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			String keyword = "%" + search + "%";
			ps.setString(1, keyword.toUpperCase());
			ps.setString(2, keyword.toLowerCase());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Amsp amsp = new Amsp();
				amsp.setID_amsp(rs.getInt("id_amsp"));
				amsp.setNom_amsp(rs.getString("nom"));
				amsp.setPrenom_amsp(rs.getString("prenom"));
				amsp.setTel_amsp(rs.getString("telephone"));
				amsp.setEmail_amsp(rs.getString("email"));
				amsp.setFonction_amsp(rs.getString("fonction"));
				
				list.add(amsp);
				
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Amsp>();
	}
	
	public Amsp select(String fnm) {
		
		String query = "select * from amsp where nom = ?";
		
		Amsp amsp = new Amsp();
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			ps.setString(1, fnm);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				amsp.setID_amsp(rs.getInt("id_amsp"));
				amsp.setNom_amsp(rs.getString("nom"));
				amsp.setPrenom_amsp(rs.getString("prenom"));
				amsp.setEmail_amsp(rs.getString("email"));
				amsp.setTel_amsp(rs.getString("telephone"));
				amsp.setFonction_amsp(rs.getString("fonction"));
			}
			
			return amsp;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new Amsp();
	}
	
	public List<Amsp> listAmsp(){
		
		String query = "select * from amsp;";
		
		List<Amsp> listAmsp = new ArrayList<>();
		
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Amsp amsp = new Amsp();
				amsp.setID_amsp(rs.getInt("id_amsp"));
				amsp.setNom_amsp(rs.getString("nom"));
				amsp.setPrenom_amsp(rs.getString("prenom"));
				amsp.setTel_amsp(rs.getString("telephone"));
				amsp.setEmail_amsp(rs.getString("email"));
				amsp.setFonction_amsp(rs.getString("fonction"));
				
				listAmsp.add(amsp);
			}
			
			return listAmsp;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return new ArrayList<Amsp>();
	}
	
	public boolean exists(String fnm, String psw) {
		
		try {
			String query = "select id_amsp from amsp where nom = ? and password = ?";
			
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, fnm.toUpperCase());
			ps.setString(2, psw);
			
			ResultSet rs = ps.executeQuery();
			
			int count = 0;
			if (rs.next()) {
			    count = rs.getInt(1); // ou rs.getInt("COUNT(*)")
			}

			if (count > 0) {
			    return true;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean create(Amsp obj) {
		
		String query = "insert into amsp(nom,prenom,telephone,email,fonction,password) values(?,?,?,?,?,?);";
		
		try {
			
			PreparedStatement cs = this.cnx.prepareStatement(query);
			cs.setString(1, obj.getNom_amsp().toUpperCase());
			cs.setString(2, obj.getPrenom_amsp().toLowerCase());
			cs.setString(3, obj.getTel_amsp());
			cs.setString(4, obj.getEmail_amsp());
			cs.setString(5, obj.getFonction_amsp());
			cs.setString(6, obj.getPassword());
	
			int rows = cs.executeUpdate();
			
			return rows > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean update(Amsp obj) {
		
		String query = "update amsp set nom = ?, prenom = ?, telephone = ?,"
				+ "email = ?, fonction = ?, password = ? where id_amsp = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setString(1, obj.getNom_amsp().toUpperCase());
			ps.setString(2, obj.getPrenom_amsp().toLowerCase());
			ps.setString(3, obj.getTel_amsp());
			ps.setString(4, obj.getEmail_amsp());
			ps.setString(5, obj.getFonction_amsp());
			ps.setString(6, obj.getPassword());
			ps.setInt(7, obj.getID_amsp());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Amsp obj) {

		String query = "delete from amsp where id_amsp = ?";
		
		try {
			PreparedStatement ps = this.cnx.prepareStatement(query);
			
			ps.setInt(1, obj.getID_amsp());
			
			int res = ps.executeUpdate();
			
			return res > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
