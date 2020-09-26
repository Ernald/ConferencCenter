package com.conferencecenter.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.conferencecenter.*;
import com.conferencecenter.daos.*;
import java.sql.*;

@WebServlet("/CheckCredencials")
public class CheckCredencials extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub response.setContentType("text/html");
	 * 
	 * String na = request.getParameter("name");
	 * 
	 * 
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * //session.setAttribute("name", na );
	 * 
	 * ServletOutputStream out = response.getOutputStream();
	 * 
	 * out.println("Your name is "+ na);
	 * 
	 * }
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	/*	String na = request.getParameter("name");
	  
		
		
		HttpSession session = request.getSession();
		
		//session.setAttribute("name", na );
		
		ServletOutputStream out = response.getOutputStream();
		
		out.println("Your name is "+ na);*/
		
			
			/*
			 * java.sql.Connection con = DatabaseConnection.initializeDatabase();
			 * 
			 * 
			 * Statement st = con.createStatement(); 
			 * String Query
			 * ="INSERT INTO Salla (EmerSalle, NumerVendesh, KostoDitore) VALUES ('Salla2', 40, 900);"
			 * ;
			 * 
			 * 
			 * st.executeUpdate(Query); st.close(); con.close();
			 */
			
			/*
			 * java.sql.Connection con = (Connection)
			 * DatabaseConnection.initializeDatabase();
			 * 
			 * Statement stmt = (Statement) con.createStatement();
			 * 
			 * String SQL =
			 * "INSERT INTO Salla (EmerSalle, NumerVendesh, KostoDitore) VALUES ('Salla3', 70, 1000);"
			 * ;
			 * 
			 * ((java.sql.Statement) stmt).executeUpdate(SQL);
			 * 
			 * 
			 * 
			 * stmt.close(); con.close();
			 */
			
			
			try
			{	    

			     UserObj user = new UserObj();
			     user.setUserName(request.getParameter("Uname"));
			     user.setPassword(request.getParameter("password"));
			     

			     user = UserDAO.login(user);
				   		    
			     if (user.isValid())
			     {
			    	 if (user.getStatus()==1) {
			    	    
			          HttpSession session = request.getSession(true);	    
			          session.setAttribute("currentSessionUser",user); 
			          if(user.getRolID()==0) {
			        	  response.sendRedirect("User/UserLoggedIN.jsp"); //logged-in page  
			          }
			          else if(user.getRolID()==1) {
			        	  
			        	  response.sendRedirect("admin/admin.jsp"); //logged-in page 
			        
			          		}
			        }
			    	 else response.sendRedirect("disabled.html");
			     }
				        
			     else 
			          response.sendRedirect("LoginInvalid.jsp"); //error page 
			} 
					
					
			catch (Throwable theException) 	    
			{
			     System.out.println(theException); 
			}
			
			
			
			
			       }
				
		
		
		
		
	}


