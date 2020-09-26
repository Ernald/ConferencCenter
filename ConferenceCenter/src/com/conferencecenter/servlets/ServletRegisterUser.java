package com.conferencecenter.servlets;
import com.conferencecenter.*;
import com.conferencecenter.daos.UserDAO;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
	
	
	
	
	
@WebServlet("/servletRegister")
	public class ServletRegisterUser extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					try
					{	  
						SecureRandom random = new SecureRandom();
				        byte[] salt = new byte[16];
				        random.nextBytes(salt);
				        
				        String pass =  request.getParameter("userPass");
				        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
				        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
				        byte[] hash = factory.generateSecret(spec).getEncoded();

					     UserObj user = new UserObj();
					     user.setFirstName(request.getParameter("emri"));
					     user.setLastName(request.getParameter("mbiemri"));
					     user.setUserName(request.getParameter("userName"));
					     user.setPassword(Base64.getEncoder().encodeToString(hash));
					     user.setEmail(request.getParameter("userEmail"));
					     user.setSalt(Base64.getEncoder().encodeToString(salt));
					     

					     user = UserDAO.Register(user);
						   		    
					     if (user.isValid())
					     {
					          HttpSession session = request.getSession(true);	    
					          session.setAttribute("currentSessionUser",user); 
					          response.sendRedirect("User/UserLoggedIN.jsp"); //logged-in page      		
					     }
						        
					     else 
					          response.sendRedirect("InvalidSignup.jsp"); //error page 
					} 
							
							
					catch (Throwable theException) 	    
					{
					     System.out.println(theException); 
					}
					
					
					
					
					       }
						
				
				
				
		
		
		
		
		
		
		

}
