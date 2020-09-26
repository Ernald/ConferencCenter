package com.conferencecenter.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import com.conferencecenter.*;
import com.conferencecenter.daos.AksesoreDAO;
import com.conferencecenter.daos.SallaDAO;

@WebServlet("/UpdateSalla")
public class ServletUpdateSalla  extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	int id=Integer.parseInt(req.getParameter("id"));
            String emri=req.getParameter("name");
            int kapacitet=Integer.parseInt(req.getParameter("kapacitet"));
            float kosto=Float.parseFloat(req.getParameter("kosto"));

            int r=SallaDAO.updateSalla(id,emri,kapacitet,kosto);
            
            ArrayList<AksesoreObj> listaA = AksesoreDAO.getAksesoreForSalla(id);
            for(AksesoreObj a : listaA) {
            	String result = req.getParameter(Integer.toString(a.getID()));
            	if (result==null)
            		AksesoreDAO.updateAksesore(a.getID(),null);
            }
            
            ArrayList<AksesoreObj> listaB = AksesoreDAO.getFreeAksesore();
            for(AksesoreObj a : listaB) {
            	String result = req.getParameter(Integer.toString(a.getID()));
            	if (result!=null && result.equals("on"))
            		AksesoreDAO.updateAksesore(a.getID(),id);
            }
            
            resp.sendRedirect("admin/salla.jsp");
        }


        catch (Throwable theException)
        {
            System.out.println("error ne servletUpdateSalla "+ theException);
        }
    }
}

