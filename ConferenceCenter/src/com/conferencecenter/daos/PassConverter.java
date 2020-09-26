package com.conferencecenter.daos;
import java.sql.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import com.conferencecenter.*;

public class PassConverter {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static String pass2=null;
	
	
   static String  convertPass(UserObj obj) {
		Statement stmt = null;    
	
	String username=obj.getUsername();
	String searchQuery= "select Salt from [dbo].[Users] where username='"
            + username + "'";
	
	 try 
	   {
	      //connect to DB 
	      currentCon =  DatabaseConnection.initializeDatabase();
	      stmt= currentCon.createStatement();
	      rs = stmt.executeQuery(searchQuery);	        
	      boolean more = rs.next();
	      String salt = rs.getString("Salt");
	      String password = obj.getPassword(); 
	      
	      
	      byte[] salt2 = Base64.getDecoder().decode(salt);
	      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt2, 65536, 128);
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        byte[] hash = factory.generateSecret(spec).getEncoded();
	 
	         pass2=Base64.getEncoder().encodeToString(hash);
		       
	      // if user does not exist set the isValid variable to false
	      if (!more) 
	      {
	         System.out.println("Nuk eshte marre rezultat nga passwordConverter");
	      } 
		        
	      //if user exists set the isValid variable to true
	      else if (more) 
	      {
	    	  System.out.println("Eshte marre rezultat nga passwordConverter resultset");
	      }
	   } 

	   catch (Exception ex) 
	   {
	      System.out.println("Kontrolli per salt ne passconverter nuk funksionoi  " + ex);
	   } 
		    
	   //some exception handling
	   finally 
	   {
	      if (rs != null)	{
	         try {
	            rs.close();
	         } catch (Exception e) {}
	            rs = null;
	         }
		
	      if (stmt != null) {
	         try {
	            stmt.close();
	         } catch (Exception e) {}
	            stmt = null;
	         }
		
	      if (currentCon != null) {
	         try {
	            currentCon.close();
	         } catch (Exception e) {
	         }

	         currentCon = null;
	      }
	   }
	
	
	return pass2;
}
	

}
