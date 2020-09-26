package com.conferencecenter.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.conferencecenter.daos.EventeDAO;

@WebServlet("/Shqyrto")
public class ServletShqyrtoRezervime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String eventID = req.getParameter("eventID");
            int status = Integer.parseInt(req.getParameter("status"));
            int a = EventeDAO.ndryshoStatus(eventID, status);

            if (a == 1)
                resp.getWriter().print("EVENTI " + eventID + " ndryshoi status me sukses");
            else resp.getWriter().print("salla eshte e zene");

        } catch (Throwable theException) {
            System.out.println("error ne servletShqyrtoRezervim " + theException);
        }
    }

}

