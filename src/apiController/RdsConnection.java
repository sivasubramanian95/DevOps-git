package apiController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RdsConnection implements RequestHandler<RequestClass, ResponseClass> {
	Statement statment;
	ResultSet resultSet;
	
	public ResponseClass handleRequest(RequestClass request, Context context) {
		String greetingString = String.format("Param %s, %s, %s, %s, %s, %s.", request.serviceNo, request.markerNo, request.ipAddr, request.targetUrl
				, request.cookie, request.cookiePw, request.date, request.maxAge, request.userAgent);
		try {
			insertApiCpc(request, context);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseClass(greetingString);
	}
	
	
	public void insertApiCpc(RequestClass request, Context context) throws SQLException {
		
		Connection dbConn = null;
		Statement statement = null;
		
		String insertQuery = "insert into api(serviceNo, markerNo, ipAddr, targetUrl, cookie, cookiePw, date, maxAge, userAgent) "
				+ "values("
				+ request.serviceNo + ","
				+ request.markerNo + ","
				+ "'" + request.ipAddr + "'," 
				+ "'" + request.targetUrl + "',"
				+ "'" + request.cookie + "',"
				+ "'" + request.cookiePw + "',"
				+ "'" + request.date + "',"
				+ "'" + request.maxAge + "',"
				+ "'" + request.userAgent + "'"
				+ ")";
		
		context.getLogger().log("Insert query : " + insertQuery);
		
		try {
			dbConn = getDBConnection(context);
			statement = dbConn.createStatement();
			statement.executeUpdate(insertQuery);
		} catch (SQLException e) {
			context.getLogger().log(e.getMessage());
		} finally {
			if(statement != null && dbConn != null) {
				statement.close();
				dbConn.close();
				context.getLogger().log("======== System Done! ==========");
			}
		}
	}
	
	// DB connection info
	private static Connection getDBConnection(Context context) {
		Connection dbConnection = null;

		String url	= "jdbc:mariadb://localhost:3306/testDB";
		String user	= "test";
		String password	= "test";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			context.getLogger().log(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(url, user, password);
			return dbConnection;
		} catch (SQLException e) {
			context.getLogger().log(e.getMessage());
		}
		return dbConnection;
	}
}