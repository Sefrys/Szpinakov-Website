package com.servlet.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ireneusz Zagan on 16.04.18, 16:23
 * Contact: sefrys@gmail.com
 */
@WebServlet("/admin/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Invalidate session
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
