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


@WebServlet(name= "UserUpdateServlet", urlPatterns= "/update")
public class UserUpdateServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        User user = UserService.getInstance().getUserById(id);
        req.setAttribute("user", user);

        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/update.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(id, login, email, password);
        UserService.getInstance().updateUser(user);
        UserServiceServlet userServiceServlet = new UserServiceServlet();
        userServiceServlet.doGet(req, resp);
    }
}
