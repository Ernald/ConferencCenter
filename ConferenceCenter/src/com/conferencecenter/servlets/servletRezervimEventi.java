package com.conferencecenter.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import com.conferencecenter.*;
import com.conferencecenter.daos.EventeDAO;
import com.conferencecenter.daos.SallaDAO;



@WebServlet("/servletRezervimEventi.java")
public class servletRezervimEventi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
		{	  
			
			 
			HttpSession session = request.getSession();
			UserObj user = (UserObj) session.getAttribute("currentSessionUser"); 
			String name = user.getUsername();
			
			java.sql.Date date=Date.valueOf(request.getParameter("data"));

		   
		    
		  
			
            
			
		     EventObj  eventi = new EventObj();
		     eventi.setUsername(name);
		     eventi.setEventname(request.getParameter("emerEventi"));
		     eventi.setPershkrim(request.getParameter("pershkrim"));
		     
		     eventi.setData(date);
		    
		     eventi.setStatusi(0);
		     eventi.setSallaID( SallaDAO.getSallaID(request.getParameter("salla")));
		    

		     EventeDAO.Rezervo(eventi);
		    
		     response.sendRedirect("User/EventeUseri.jsp");
			   		    
		      
		}		
				
		catch (Throwable theException) 	    
		{
		     System.out.println("Nuk punon dergimi i formes se rezervimit te salles "+ theException); 
		}
		
		
		
		
	}
	
	

}
