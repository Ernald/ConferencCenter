package com.conferencecenter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/User/*")
public class AksesFilterU implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    try {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession session = httpRequest.getSession(false);
      
  
      
      boolean isLoggedIn = (session != null && session.getAttribute("currentSessionUser")!=null );

      if (!isLoggedIn) {

        RequestDispatcher dispatcher = request.getRequestDispatcher("../AccessDeniedUser.jsp");
        dispatcher.forward(request, response);
      }

      else
          chain.doFilter(request, response);

    }
catch (Throwable theException)
    {
        System.out.println("error filtriU "+ theException);
    }
  }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}