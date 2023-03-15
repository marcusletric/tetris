package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility to establish database connection.
 */
public class SQLConnUtil {
		
	public String dbms = "mysql";
	public String dbName = "javabase"; 
	public String userName = "java";
	public String password = "d$7hF_r!9Y";
	public String urlString = "localhost:3306";
  
	  private String serverName = "localhost";
	  private int portNumber = 3306;
	  
	  /**
	   * Try to connect to the database.
	   * 
	   * @return established connection
	   * @throws SQLException if there was an error during connection 
	   */
	  public Connection getConnection() throws SQLException {
		    Connection conn = null;
		    Properties connectionProps = new Properties();
		    connectionProps.put("user", this.userName);
		    connectionProps.put("password", this.password);
		    
		    String currentUrlString = null;
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } 
		    catch (ClassNotFoundException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } 

		    if (this.dbms.equals("mysql")) {
		      currentUrlString = "jdbc:" + this.dbms + "://" + this.serverName +
		                                      ":" + this.portNumber + "/" + this.dbName;
		      conn =
		          DriverManager.getConnection(currentUrlString,
		                                      connectionProps);
		      
		      this.urlString = currentUrlString + this.dbName;
		      conn.setCatalog(this.dbName);
		    } else if (this.dbms.equals("derby")) {
		      this.urlString = "jdbc:" + this.dbms + ":" + this.dbName;
		      
		      conn =
		          DriverManager.getConnection(this.urlString + 
		                                      ";create=true", connectionProps);
		      
		    }
		    //System.out.println("Connected to database");
		    return conn;
		  }
}
