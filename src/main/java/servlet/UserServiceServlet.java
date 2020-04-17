package servlet;


import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServiceServlet", urlPatterns= "/login")
public class UserServiceServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        userService.createTable();
        List<User> users = new ArrayList<>();
        users = userService.getAllUser();
        req.setAttribute("userList", users);


        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        userService.addUser(new User(login, email, password));
        doGet(req, resp);
    }
}
