package com.conferencecenter.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.conferencecenter.*;

public class EventeDAO	{
   static Connection currentCon = null;
   static ResultSet rs = null;  
 
	
   public static void Rezervo(EventObj Obj) {
		
	      //preparing some objects for connection 
	      Statement stmt = null;    
		
	      
	      	String  us = Obj.getUsername();
	      	String  en = Obj.getEventname();
	      	String  pr = Obj.getPershkrim();
		    Date     dt= Obj.getData();
		    Integer st = Obj.getStatusi();
		    Integer sid= Obj.getSallaID();
		    
		    
		    
	 
		    

		     String InsertQuery= "INSERT INTO Evente (TitullEventi, Username, PershkrimEventi, DateEventi, Status, SallaID) VALUES('"+ 
		                     												
		    		 														en +
		                     												"','"+
		                     												us +
		                     												"','"+
		                     												pr +
		                     												"','"+
		                     												dt+
		                     												"',"+
		                     												st +
		                     												","+
		                     												sid+
		                     												")";
		                     												
		    
	  
	   try 
	   {
	      //connect to DB 
	      currentCon =  DatabaseConnection.initializeDatabase();
	      stmt=currentCon.createStatement();
	      	
	      int a= stmt.executeUpdate(InsertQuery);
	      
	    
	      if (a==1) 
	      {
	         System.out.println("U shtua eventi ne databaze");
	         
	         
	 		
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


	}
	
   public static ArrayList<EventObj> merrEvente(ArrayList<EventObj> arrli, HttpServletRequest request) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	
      HttpSession session = request.getSession();
		UserObj user = (UserObj) session.getAttribute("currentSessionUser"); 
		String name = user.getUsername();
		System.out.println("username tek infoevente eshte "+ name);
	    
      String searchQuery =
            "select * from Evente where username='"+ name+"'";
	    
      System.out.println("searchquery tek infoevente eshte "+ searchQuery);
   try 
   {
	   
      //connect to DB 
      currentCon =  DatabaseConnection.initializeDatabase();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	 
      
       while(rs.next()) {
    	   
    	   
				
				  Date date = rs.getDate("DateEventi");
				 
    	   
    	   String Teventi = rs.getString("TitullEventi");
    	  
    	   
    	   Integer status = rs.getInt("Status");
    	   Integer IDsalle = rs.getInt("SallaID");
    	   
    	   
    	   
           
          
    	   
    	   EventObj ob = new EventObj();
    	  
    	   ob.setSallaID(IDsalle);
    	  
    	   ob.setData(date); 
    	  
    	   ob.setEventname(Teventi);
    	  
    	   ob.setStatusi(status);
    	   arrli.add(ob);
    	   
    	  
    	   
    	   
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


return arrli;
   	
   }
   
   public static ArrayList<EventObj> merrEventeMeDate(ArrayList<EventObj> arrli, HttpServletRequest request,String dt) throws ParseException {
		
	      //preparing some objects for connection 
	      Statement stmt = null;    
		
	      HttpSession session = request.getSession();
			UserObj user = (UserObj) session.getAttribute("currentSessionUser"); 
			String name = user.getUsername();
			System.out.println("username tek infoevente eshte "+ name);
			
			String sDate1=dt; 
			
		    Date date1=java.sql.Date.valueOf(sDate1);
		    
	      String searchQuery =
	            "select * from Evente where username='"+ name+"'" +" AND DateEventi >= '"+ date1+"'";
		    
	      System.out.println("searchquery tek eventeDAO eshte "+ searchQuery);
	   try 
	   {
		   
	      //connect to DB 
	      currentCon =  DatabaseConnection.initializeDatabase();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery(searchQuery);	 
	      
	       while(rs.next()) {
	    	   
	    	   
					
					  Date date = rs.getDate("DateEventi");
					 
	    	   
	    	   String Teventi = rs.getString("TitullEventi");
	    	  
	    	   
	    	   Integer status = rs.getInt("Status");
	    	   Integer IDsalle = rs.getInt("SallaID");
	    	   
	    	   
	    	   
	           
	          
	    	   
	    	   EventObj ob = new EventObj();
	    	  
	    	   ob.setSallaID(IDsalle);
	    	  
	    	   ob.setData(date); 
	    	  
	    	   ob.setEventname(Teventi);
	    	  
	    	   ob.setStatusi(status);
	    	   arrli.add(ob);
	    	   
	    	  
	    	   
	    	   
	       }
	       
	   
	   }
			
		       
	    
		        
	     
	   catch (Exception ex) 
	   {
	      System.out.println("eventeDAO  Connect fail " + ex);
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


	return arrli;
	   	
	   }
 
   
   public static ArrayList<EventObj> getEvente(){
       ArrayList<EventObj> list = new ArrayList<EventObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Evente];";

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next())
           {
               list.add(new EventObj(rs.getInt("EventID"),rs.getString("Username"),rs.getString("TitullEventi"),rs.getString("PershkrimEventi"),rs.getDate("DateEventi"),rs.getInt("Status"),rs.getInt("SallaID")));
           }
           rs.close();
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't get events from db : An Exception has occurred! " + ex);
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

   public static int ndryshoStatus(String eventID,int status){
       Statement stmt = null;
       int i=-2;

       tryLabel:
       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           if(status==-1){
                i = stmt.executeUpdate("UPDATE Evente " +
                       "SET Status=-1 " +
                       "WHERE EventID=" + eventID);
               break tryLabel;
           }
           rs = stmt.executeQuery("SELECT COUNT(*) NR FROM Evente " +
                   "WHERE DateEventi=(select a.DateEventi from Evente a " +
                   "WHERE a.EventID="+ eventID +") and SallaID=(Select b.SallaID from Evente b " +
                   "WHERE b.EventID="+ eventID +") AND Status=1;");
           int c=-5;
           while (rs.next())
                   c = rs.getInt("NR");
           System.out.println("nr i eventeve ne te njejten date"+ c);

           if(c==0){
               i = stmt.executeUpdate("UPDATE Evente " +
                       "SET Status=1 " +
                       "WHERE EventID=" +eventID);
           }
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't update status eventi : An Exception has occurred! " + ex);
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