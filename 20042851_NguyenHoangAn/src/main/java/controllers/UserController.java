package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import repositories.UserRepository;
import services.UserService;

@WebServlet("/users")
public class UserController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        Connection dbConnection = (Connection) getServletContext().getAttribute("DBConnection");
        this.userService = new UserService(new UserRepository(dbConnection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "register":
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
                break;
            case "login":
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                break;
            case "list":
            default:
                List<User> users = userService.getAllUsers();
                req.setAttribute("users", users);
                req.getRequestDispatcher("/views/user_list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            handleRegister(req, resp);
        } else if ("login".equals(action)) {
            handleLogin(req, resp);
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        if (userService.registerUser(email, password, name)) {
            resp.sendRedirect(req.getContextPath() + "/users?action=login");
        } else {
            req.setAttribute("errorMessage", "Registration failed. Email might already be in use.");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.loginUser(email, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("errorMessage", "Invalid email or password.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
