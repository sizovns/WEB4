package controller;

import model.User;
import model.Role;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.getUserLoginPass(login, password);
        Role role = userService.getRole(user);

        if (user != null) {
            if (role.getRole().equalsIgnoreCase("admin")){
                request.getSession().setAttribute("user", user);
                response.sendRedirect("/admin/showAll");
            } else {
                request.getSession().setAttribute("user", user); // Put user in session.
                response.sendRedirect("/user"); // Go to some start page.
            }
        } else {
            request.setAttribute("error", "Unknown login, try again"); // Set error msg for ${error}
            request.getRequestDispatcher("index.jsp").forward(request, response); // Go back to login page.
        }
    }
}
