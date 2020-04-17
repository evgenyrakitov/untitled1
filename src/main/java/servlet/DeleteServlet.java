package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/remove")
public class DeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        UserService userService = new UserService();
        User user = userService.getUserById(id);
        userService.removeUser(user);
        UserServiceServlet userServiceServlet = new UserServiceServlet();
        userServiceServlet.doGet(req, resp);

    }
}
