package tablerecords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {

	public static Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/tailor_project","root","");
			System.out.println("CONGO");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}