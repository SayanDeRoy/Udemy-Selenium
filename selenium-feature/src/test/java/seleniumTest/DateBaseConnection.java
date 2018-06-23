package seleniumTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DateBaseConnection {
	
	@Test
	public void connectDB() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String userName = "SYSTEM";
		String password = "Infosys21$";
		String hostName = "localhost";
		String portNo = "1521";
		String sid = "orcl";
		
		String url = "jdbc:oracle:thin:@"+hostName+":"+portNo+":"+sid;
		
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select max(sal) as \"Salary\" from emp where sal<(select max(sal) from emp)");
		
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			Assert.assertEquals(rs.getInt(1), 4000);
		}
	}
}
