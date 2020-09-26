package com.conferencecenter.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.conferencecenter.*;

public class AksesoreDAO
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
 
   public static ArrayList<String> merrAksesore(ArrayList<String> aksesore, String emerSalle) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	  Integer sid = SallaDAO.getSallaID(emerSalle);     
	    
      String searchQuery =
            "select * from Aksesore where SallaID="+ sid +" AND GjendjeAksesori=1";
	    
      System.out.println("searchquery tek infoevente eshte "+ searchQuery);
   try 
   {
	   
      //connect to DB 
	   boolean flag= false;
      currentCon =  DatabaseConnection.initializeDatabase();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	 
      
       while(rs.next()) {
    	   
    	   
				
				  
				 
    	   
    	   String emeraksesori = rs.getString("EmerAksesori");
    	   aksesore.add(emeraksesori);
    	   
    	   
       }
       
   
   }
		
	       
    
	        
     
   catch (Exception ex) 
   {
      System.out.println("InfoeventeDAO  Connect fail " + ex);
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


return aksesore; 	
   }
   
   
   public static ArrayList<AksesoreObj> getAksesore(){
       ArrayList<AksesoreObj> list = new ArrayList<AksesoreObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Aksesore];";

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next())
           {
        			   
               list.add(new AksesoreObj(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getInt(3)));
           }
           rs.close();
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't get aksesore from db : An Exception has occurred! " + ex);
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
   

   public static int deleteAksesore(int aksesorID){
       Statement stmt = null;
       int a=-2;
       String query = "DELETE from [dbo].[Aksesore] " +
               "WHERE AksesorID="+aksesorID;

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           a= stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("aksesor succesfully deleted");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't delete aksesore from db : An Exception has occurred! " + ex);
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

       return a;
   }
   

   public static int addAksesore(String name,int gjendja,Integer sallaID){
       Statement stmt = null;
       int a=-2;
       String query = "INSERT INTO  [dbo].[Aksesore] " +
               "VALUES('"+name+"',"+sallaID+","+gjendja+");";

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           a= stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("aksesor succesfully created");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't create aksesore from db : An Exception has occurred! " + ex);
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

       return a;
   }

   
   public static int updateAksesore(int aksesorID,String emri,int gjendja,Integer sallaID){
       Statement stmt = null;
       int a=-2;
       String query = "update [dbo].[Aksesore] " +
               "set EmerAksesori='"+ emri +"',SallaID="+sallaID+",GjendjeAksesori="+gjendja+
               " WHERE AksesorID="+aksesorID;

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           a= stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("aksesor succesfully updated");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't update aksesore: An Exception has occurred! " + ex);
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

       return a;
   }
   
   public static int updateAksesore(int aksesorID,Integer sallaID){
       Statement stmt = null;
       int a=-2;
       String query = "update [dbo].[Aksesore] " +
               "set SallaID="+sallaID+
               " WHERE AksesorID="+aksesorID;

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           a= stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("aksesor succesfully updated");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't update aksesore: An Exception has occurred! " + ex);
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

       return a;
   }
   
   
   public static ArrayList<AksesoreObj> getFreeAksesore(){
       ArrayList<AksesoreObj> list = new ArrayList<AksesoreObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Aksesore] where SallaID is NULL and GjendjeAksesori=1;";

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next())
           {
        			   
               list.add(new AksesoreObj(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getInt(3)));
           }
           rs.close();
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't get aksesore from db : An Exception has occurred! " + ex);
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
   
   
   public static ArrayList<AksesoreObj> getAksesoreForSalla(int sallaID){
       ArrayList<AksesoreObj> list = new ArrayList<AksesoreObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Aksesore] where SallaID="+sallaID;

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next())
           {
        			   
               list.add(new AksesoreObj(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getInt(3)));
           }
           rs.close();
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't get aksesore from db : An Exception has occurred! " + ex);
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

}