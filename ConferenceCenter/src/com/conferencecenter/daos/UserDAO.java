package com.conferencecenter.daos;


import java.sql.*;
import java.util.ArrayList;
import com.conferencecenter.*;

public class UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
   
	
   public static UserObj login(UserObj Obj) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	
      String username = Obj.getUsername();    
      String passwordHash = PassConverter.convertPass(Obj);   
	    
      String searchQuery =
            "select * from [dbo].[Users] where username='"
                     + username
                     + "' AND password='"
                     + passwordHash
                     + "'";
	    
   try 
   {
      //connect to DB 
      currentCon =  DatabaseConnection.initializeDatabase();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	        
      boolean more = rs.next();
    
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Ju nuk jeni User i regjistruar, beni regjistrim");
         Obj.setValid(false);
         
 		
      } 
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {
         String firstName = rs.getString("Emer");
         String lastName = rs.getString("Mbiemer");
         int Rolid = rs.getInt("RolID");
         int status= rs.getInt("Status");
         
         
         Obj.setFirstName(firstName);
         Obj.setLastName(lastName);
        Obj.setValid(true);
        Obj.setRolID(Rolid);
        Obj.setStatus(status);
        
            }
   } 

   catch (Exception ex) 
   {
      System.out.println("Log In fail,  Userdao :  " + ex);
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

return Obj;
	
   }	
   
   public static UserObj Register(UserObj Obj) {
		
	      //preparing some objects for connection 
	      Statement stmt = null;    
		
	      String Username = Obj.getUsername();    
	      String Password = Obj.getPassword();   
	      String Emri = Obj.getFirstName();   
	      String Mbiemri = Obj.getLastName();   
	      String Email = Obj.getEmail();   
	      String Salt = Obj.getSalt();   
		    
	    
	     String InsertQuery= "INSERT INTO Users (Emer, Mbiemer, Username, Password, Email, Salt) VALUES('"+ 
	                     												
	    		 														Emri +
	                     												"','"+
	                     												Mbiemri +
	                     												"','"+
	                     												Username +
	                     												"','"+
	                     												Password+
	                     												"','"+
	                     												Email+
	                     												"','"+
	                     												Salt+
	                     												"')";
	                     												
	  
	   try 
	   {
	      //connect to DB 
	      currentCon =  DatabaseConnection.initializeDatabase();

	      
	       stmt = currentCon.createStatement();

			 int a= stmt.executeUpdate(InsertQuery);
			
			 
			 if (a==1) 
		      {
		         System.out.println("Ju u regjistruat");
		         Obj.setValid(true);
		         
		 		
		      } 
			 
			 else if (a==0){
				 
				Obj.setValid(false); 
				 
				 
			 }
		
	 
	      
	   }

	   catch (Exception ex) 
	   {
	      System.out.println("Regjistrimi nuk funksionoi, error: " + ex);
	   } 
		    
	 
	   //some exception handling
	   finally 
	   {
	      
		
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

	return Obj;
   }
	   
   
   public static ArrayList<UserObj> getUsers(){
       ArrayList<UserObj> list = new ArrayList<UserObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Users];";

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next())
           {
               list.add(new UserObj(rs.getString("Username"),rs.getString("Emer"),rs.getString("Mbiemer"),rs.getString("Email"),rs.getInt("RolID"),rs.getInt("Status")));
           }
           
           rs.close();
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't get users from db : An Exception has occurred! " + ex);
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

       return list;
   }
   
   
   public static int ndryshoStatus(String username){     //activate or disable user
       Statement stmt = null;
       int i=-2;

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           i = stmt.executeUpdate("UPDATE Users "+
           "SET Status=(CASE WHEN(Status=0) THEN 1 ELSE 0 END)"
           		+ " WHERE Username='" + username+"'");
           
           if (i == 1)
               System.out.println("status of user succesfully changed");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't update user status from db : An Exception has occurred! " + ex);
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

       return i;
   }

   public static int ndryshoRol(String username){
       Statement stmt = null;
       int i=-2;

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           i = stmt.executeUpdate("UPDATE Users SET RolID = ( CASE WHEN(RolID=0) THEN 1 ELSE 0 END) WHERE Username='" + username+"'");
           if (i == 1)
               System.out.println("role succesfully changed");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't change role db : An Exception has occurred! " + ex);
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

       return i;
   }
   
}