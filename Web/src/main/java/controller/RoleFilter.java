package controller;


import model.User;
import model.Role;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/admin/*", "/user"})
public class RoleFilter implements Filter {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/index.jsp";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        User user = null;
        Role role = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
            role = userService.getRole(user);
        }

        if (loggedIn || loginRequest) {
            if (role.getRole().equalsIgnoreCase("admin")) {
                filterChain.doFilter(request, response); // User is logged in, just continue request.
            } else {
                response.sendRedirect("/");
            }
        } else {
            response.sendRedirect("/"); // Not logged in, show login page.
        }

    }

    @Override
    public void destroy() {

    }
}
