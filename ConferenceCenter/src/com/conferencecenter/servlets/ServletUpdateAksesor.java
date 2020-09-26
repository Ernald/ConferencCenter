package com.conferencecenter.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.conferencecenter.daos.AksesoreDAO;

@WebServlet("/UpdateAks")
public class ServletUpdateAksesor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id=Integer.parseInt(req.getParameter("id"));
            String emri=req.getParameter("name");
            int gjendja=Integer.parseInt(req.getParameter("gjendja"));
            Integer sallaID=Integer.parseInt(req.getParameter("sallaID"));
            
            if(sallaID==0)
            	sallaID=null;

            int a=AksesoreDAO.updateAksesore(id,emri,gjendja,sallaID);
            resp.sendRedirect("admin/aksesore.jsp");
        }


                catch (Throwable theException)
        {
            System.out.println("error ne servletUpdateAksesor "+ theException);
        }
    }
}
