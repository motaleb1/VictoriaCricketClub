import java.sql.*;

import javax.swing.*;

public class DbUtil
{
		Statement stmt;
		Connection conn;
		public DbUtil()
		{
			try
			{
				 Class.forName("com.mysql.jdbc.Driver");// Loading the class

				 // Create a connection through the DriverManager
				 conn = DriverManager.getConnection("jdbc:mysql://localhost/victoriacricketclub", "root", "");
				 stmt = conn.createStatement(); // create a statement
				 //System.out.println("Connected");
			}

			catch(Exception cnfe)
			{
				JOptionPane.showMessageDialog(null,cnfe);
			}

		}

		/*public void showData()
		{
			try
			{
				ResultSet rs=stmt.executeQuery("select * from bus_driver");
				while(rs.next())
				{
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getString(4));
					System.out.println(rs.getString(5));
				}
			}

			catch(SQLException sqle)
			{

			}
		}*/
}