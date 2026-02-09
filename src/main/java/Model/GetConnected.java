package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnected {

	private static final String url = "jdbc:mysql://localhost:3307/pfe_chirurgie";
	private static final String unm = "root";
	private static final String psw = "msnsn";
	
	public static Connection getConnected() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, unm, psw);
	}
}
