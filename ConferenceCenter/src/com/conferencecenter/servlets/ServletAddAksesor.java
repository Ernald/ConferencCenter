package com.conferencecenter.servlets;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.conferencecenter.daos.AksesoreDAO;
import java.io.IOException;

@WebServlet("/AddAks")
public class ServletAddAksesor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name= req.getParameter("name");
            int gjendja=Integer.parseInt(req.getParameter("gjendja"));
            System.out.println(req.getParameter("sallaID"));
            Integer sallaID= Integer.parseInt(req.getParameter("sallaID"));
            
            if(sallaID==0)
            	sallaID=null;

            int a = AksesoreDAO.addAksesore(name,gjendja,sallaID);
            resp.sendRedirect("admin/aksesore.jsp");


        }
        catch (Throwable theException)
        {
            System.out.println("error ne servletAddAksesor "+ theException);
        }
    }
}