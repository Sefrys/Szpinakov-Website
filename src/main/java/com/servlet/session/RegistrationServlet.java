package com.servlet.session;

import com.model.UserModel;
import com.service.RegistrationService;
import com.sha.PasswordHash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ireneusz Zagan on 16.04.18, 17:25
 * Contact: sefrys@gmail.com
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username").toLowerCase();
        String email = request.getParameter("email").toLowerCase();
        String password = null;

        try {
            password = PasswordHash.sha256(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        UserModel newUser = new UserModel(username, email, password);
        RegistrationService service = new RegistrationService();

        if(service.registerNewUser(newUser)) {

            // Get old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if(oldSession != null) {
                oldSession.invalidate();
            }

            // Generate new session
            HttpSession newSession = request.getSession(true);

            // Setting session to expire in 15 minutes
            newSession.setMaxInactiveInterval(15*60);

            Cookie message = new Cookie("message", "Welcome new user");
            response.addCookie(message);
            response.sendRedirect("admin/RegistrationSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/registerPage.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Username or email already taken.</font>");
            rd.include(request, response);
        }
    }
}
