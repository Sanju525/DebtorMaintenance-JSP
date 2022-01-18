package project.bfour.debtormaintenance.controller.login;

import project.bfour.debtormaintenance.dao.UserDao;
import project.bfour.debtormaintenance.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "UserRegServlet", value = "/user/register")
public class UserRegServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
        request.getRequestDispatcher("/user_register.jsp").include(request, response); // view
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            registerUser(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        response.setContentType("text/html");
        User u = new User(); // object
        UserDao userDao = new UserDao(); // database interactivity
        u.setUsername(request.getParameter("username"));
        String encPassword = UserDao.applySha256(request.getParameter("password"));
        u.setPassword(encPassword);

        if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
            request.setAttribute("error", "Password mismatch");
            doGet(request, response);
        } else if (userDao.register(u) > 0) {
//            sout("Inserted into database");
            HttpSession session = request.getSession();
            session.setAttribute("message", "Debtor Account Created Successfully!");
            response.sendRedirect("/");
        } else {
            request.setAttribute("userRegistered", "error");
            doGet(request,response); // forwarding
        }

    }
}
