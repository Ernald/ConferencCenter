package com.conferencecenter.servlets;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.conferencecenter.daos.AksesoreDAO;

@WebServlet("/DeleteAks")
public class ServletDeleteAksesor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int aksesorID=Integer.parseInt(req.getParameter("aksID"));
            int a = AksesoreDAO.deleteAksesore(aksesorID);
            if (a==1)
                resp.getWriter().print("Aksesori "+aksesorID+" u fshi me sukses");

        }
        catch (Throwable theException)
        {
            System.out.println("error ne servletDeleteAksesor "+ theException);
        }
    }
}

