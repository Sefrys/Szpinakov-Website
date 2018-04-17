package com.servlet.session;

import com.service.LoginService;
import com.sha.PasswordHash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ireneusz Zagan on 16.04.18, 16:02
 * Contact: sefrys@gmail.com
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username").toLowerCase();
        String password = null;

        try {
            password = PasswordHash.sha256(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        LoginService loginService = new LoginService();

        if(loginService.authenticateUser(username, password)) {

            // Get old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if(oldSession != null) {
                oldSession.invalidate();
            }
            // Generate new session
            HttpSession newSession = request.getSession(true);

            // Setting session to expire in 15 minutes
            newSession.setMaxInactiveInterval(15*60);

            Cookie message = new Cookie("message", "Welcome");
            response.addCookie(message);
            response.sendRedirect("admin/LoginSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginPage.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Wrong username or password.</font>");
            rd.include(request, response);
        }
    }

}
