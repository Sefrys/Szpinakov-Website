package com.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ireneusz Zagan on 17.04.18, 18:00
 * Contact: sefrys@gmail.com
 */
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
        this.context.log("Authenticator Initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if(session == null) {
            this.context.log("Unauthorized access request");
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
