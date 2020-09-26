package com.conferencecenter.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.conferencecenter.*;

public class SallaDAO	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
	
   public static ArrayList<SallaObj> merrSalla(ArrayList<SallaObj> arrli) {
	
      //preparing some objects for connection 
      Statement stmt = null;    
	
       
	    
      String searchQuery =
            "select * from [dbo].[Salla]";
	    
     
   try 
   {
	   
      //connect to DB 
      currentCon =  DatabaseConnection.initializeDatabase();
      stmt=currentCon.createStatement();
      rs = stmt.executeQuery(searchQuery);	 
      
       while(rs.next()) {
    	   
    	   String emer = rs.getString("EmerSalle");
           Integer vende = rs.getInt("NumerVendesh");
           Integer kosto = rs.getInt("KostoDitore");
    	   
    	   SallaObj ob = new SallaObj();
    	   ob.setEmerSalle(emer);
    	   ob.setNumerVendesh(vende); 
    	   ob.setKostoDitore(kosto);
    	   arrli.add(ob);
    	   
    	  System.out.println(emer +""+ vende+"" + kosto);
    	   
    	   
       }
       
   
   }
		
	       
    
	        
     
   catch (Exception ex) 
   {
      System.out.println("SallaConnect fail " + ex);
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
   
   
   public static ArrayList<SallaObj> getSalla() {
       ArrayList<SallaObj> sList = new ArrayList<SallaObj>();
       Statement stmt = null;
       String query = "select * from [dbo].[Salla];";

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next()) {
               sList.add(new SallaObj(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getFloat(4)));

           }
           rs.close();

           ArrayList<AksesoreObj> aList = AksesoreDAO.getAksesore(); //mbushen sallat me aksesore
           for (int i = 0; i < sList.size(); i++) {
               for (int j = 0; j < aList.size(); j++) {
                   if (sList.get(i).getId()==aList.get(j).getSallaID())
                       sList.get(i).getAksesoreList().add(aList.get(j));
               }
           }

       } catch (Exception ex) {
           System.out.println("Couldn't get salla from db : An Exception has occurred! " + ex);
       }

       //some exception handling
       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (Exception e) {
               }
               rs = null;
           }

           if (stmt != null) {
               try {
                   stmt.close();
               } catch (Exception e) {
               }
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

       return sList;
   }

   public static ArrayList<Integer> getIDsOfSalla() {
	   
       ArrayList<Integer> list = new ArrayList<Integer>();
       Statement stmt = null;
       String query = "select SallaID from [dbo].[Salla]";

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           rs = stmt.executeQuery(query);

           while (rs.next()) {
               list.add(rs.getInt(1));
           	}
           
           rs.close();
       } 
       
       catch (Exception ex) {
           System.out.println("Couldn't get salla's ID from db : An Exception has occurred! " + ex);
       }

       //some exception handling
       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (Exception e) {
               }
               rs = null;
           }

           if (stmt != null) {
               try {
                   stmt.close();
               } catch (Exception e) {
               }
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
   

   public static int deleteSalla(int sallaID) {
       Statement stmt = null;
       int a = -2;

       String query = "DELETE from [dbo].[Salla] " +
               "WHERE SallaID=" + sallaID;

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           a = stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("Salla ID"+sallaID + " succesfully deleted");
       } 
       
       catch (Exception ex) {
           System.out.println("Couldn't delete salla from db : An Exception has occurred! " + ex);
       }

       //some exception handling
       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (Exception e) {
               }
               rs = null;
           }

           if (stmt != null) {
               try {
                   stmt.close();
               } catch (Exception e) {
               }
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

   public static int addSalla(String name, int kapacitet, float kosto) {
       Statement stmt = null;
       int a = -2;
       String query = "INSERT INTO  [dbo].[Salla] " +
               "VALUES('" + name + "'," + kapacitet + "," + kosto + ");";

       try {
           //connect to DB
           currentCon = DatabaseConnection.initializeDatabase();
           stmt = currentCon.createStatement();
           a = stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("salla succesfully created");
       } 
       
       catch (Exception ex) {
           System.out.println("Couldn't create salla from db : An Exception has occurred! " + ex);
       }

       //some exception handling
       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (Exception e) {
               }
               rs = null;
           }

           if (stmt != null) {
               try {
                   stmt.close();
               } catch (Exception e) {
               }
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
   
   
   public static int updateSalla(int id,String emri,int kapacitet,float kosto){
       Statement stmt = null;
       int a=-2;
       String query = "update [dbo].[Salla] " +
               "set EmerSalle='"+emri+"',NumerVendesh="+kapacitet+",KostoDitore="+kosto+
               " WHERE SallaID="+id;

       try
       {
           //connect to DB
           currentCon =  DatabaseConnection.initializeDatabase();
           stmt=currentCon.createStatement();
           a= stmt.executeUpdate(query);
           if (a == 1)
               System.out.println("salla succesfully updated");
       }

       catch (Exception ex)
       {
           System.out.println("Couldn't update salla: An Exception has occurred! " + ex);
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
   
   public static String gjejEmerSalle(Integer id) {
		
	      //preparing some objects for connection 
	      Statement stmt = null;    
		  String  emr=null;
	       
	      String searchQuery =
	            "SELECT [EmerSalle] FROM [dbo].[Salla] WHERE SallaID="+id;
		    
	     
	   try 
	   {
	      //connect to DB 
	      currentCon =  DatabaseConnection.initializeDatabase();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery(searchQuery);	 
	      
	       while (rs.next())
	    	   emr = rs.getString("EmerSalle");
	    	 
	   }
		        
	   catch (Exception ex) 
	   {
	      System.out.println("SallaConnect fail " + ex);
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


	return emr;
	   	
	   }

   
   public static Integer getSallaID(String emer) {
	   //preparing some objects for connection 
	   Statement stmt = null;    
	   Integer id =null;
	   
	   String searchQuery =
			   "SELECT [SallaID] FROM [dbo].[Salla] WHERE EmerSalle='"+emer+"'";
	   try 
	   {
		   //connect to DB 
		   currentCon =  DatabaseConnection.initializeDatabase();
		   stmt=currentCon.createStatement();
		   rs = stmt.executeQuery(searchQuery);
 
		   rs.next(); 
		   id = rs.getInt("SallaID");
		   System.out.println("Id e salles tek GetIdSalle : "+id);
	   }
	   catch (Exception ex) 
	   {
		   System.out.println("SallaConnect fail " + ex);
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


	   return id;

   }
   
}