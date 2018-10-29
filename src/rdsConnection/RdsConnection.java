package rdsConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RdsConnection implements RequestHandler<String, String> {
	Statement statment;
	ResultSet resultSet;
	
	@Override
	public String handleRequest(String input, Context context) {
		
		String url	= "jdbc:mariadb://localhost:3306/testDB";
		String user	= "test";
		String pw	= "pwd";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, user, pw);
			// TEST Query
			String query = "select * from user limit " + input;
			Statement statement = con.createStatement();
			resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				String output = resultSet.getString("userid");
				
				context.getLogger().log(resultSet.getString("name") + " of user ID : " + resultSet.getString("userid"));
				
				return output;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
