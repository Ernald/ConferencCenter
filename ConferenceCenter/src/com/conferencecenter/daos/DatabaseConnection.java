package com.conferencecenter.daos;

 
import java.sql.DriverManager; 
import java.sql.SQLException;


  
// This class can be used to initialize the database connection 
public class DatabaseConnection { 
	
    protected static java.sql.Connection initializeDatabase() 
        throws SQLException, ClassNotFoundException 
    { 
    	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=ConferenceCenter;integratedSecurity=true ";
         java.sql.Connection con =  DriverManager.getConnection(connectionUrl);
       
        // Initialize all the information regarding 
        // Database Connection 
		/*
		 * String dbDriver = "com.mysql.jdbc.Driver"; String
		 * url="jdbc:mysql://localhost:3306/conferencecenter"; String uname="root";
		 * String pass="root1";
		 * 
		 * 
		 * Class.forName(dbDriver);
		 * 
		 * 
		 * java.sql.Connection con = DriverManager.getConnection(url,uname,pass );
		 */
        return con; 
    } 
} 
