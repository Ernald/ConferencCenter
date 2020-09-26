package com.conferencecenter.servlets;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.conferencecenter.daos.SallaDAO;

@WebServlet("/DeleteSalla")
public class ServletDeleteSalla extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	int sallaID=Integer.parseInt(req.getParameter("sallaID"));
            int a = SallaDAO.deleteSalla(sallaID);
            if (a==1)
                resp.getWriter().print("salla ID"+sallaID +" u fshi me sukses");

        }
        catch (Throwable theException)
        {
            System.out.println("error ne servletDeleteSalla "+ theException);
        }
    }
}
