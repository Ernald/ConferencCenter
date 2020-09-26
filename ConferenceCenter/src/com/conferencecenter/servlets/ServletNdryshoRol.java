package com.conferencecenter.servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.conferencecenter.daos.UserDAO;

import java.io.IOException;

@WebServlet("/NdryshoRol")
public class ServletNdryshoRol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("usern");
            int a = UserDAO.ndryshoRol(username);
            if (a==1)
                resp.getWriter().print("Useri "+username+" ndryshoi rol me sukses");

        }
        catch (Throwable theException)
        {
            System.out.println("error ne servletNdryshoUser "+ theException);
        }

    }
}
