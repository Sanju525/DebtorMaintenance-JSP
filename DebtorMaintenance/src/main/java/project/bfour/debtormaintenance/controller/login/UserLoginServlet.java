package project.bfour.debtormaintenance.controller.login;

import project.bfour.debtormaintenance.dao.MySqlConn;
import project.bfour.debtormaintenance.dao.UserDao;
import project.bfour.debtormaintenance.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "UserLoginServlet", value = "")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").include(request,response); // view
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String who = request.getParameter("who");
        // hidden input for user_login.jsp==deb
        // hidden input for admin_login.jsp==adm
        try {
            validation(request, response, who);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void validation (HttpServletRequest request, HttpServletResponse response, String who) throws SQLException, IOException, ServletException {
        User u = new User();
        UserDao userDao = new UserDao();
        u.setUsername(request.getParameter("username"));
        String encPassword = UserDao.applySha256(request.getParameter("password"));
        u.setPassword(encPassword);
        HttpSession session = request.getSession();

        if(userDao.validate(u, who)==1) { // does user exists in out debtor table
//            sout("Checking User");
            session.setAttribute("username", u.getUsername());
            session.setAttribute("who", who);
            session.setAttribute("uid", userDao.getUid(MySqlConn.getCon(), u.getUsername()));
            if (who.equals("deb")){
                response.sendRedirect("/debtor/home");
            } else {
                response.sendRedirect("/admin/home");
            }
        } else if(userDao.validate(u, who) == 10){
            session.setAttribute("IncorrectPassword", "true");
            if (who.equals("deb")) {
                doGet(request, response);
            } else {
                response.sendRedirect("/admin/login");
            }
        } else {
//            sout("User Not Found");
            session.setAttribute("UserNotFound", "true");
            if (who.equals("deb")){
                doGet(request, response);
            } else {
                response.sendRedirect("/admin/login");
            }
        }
    }
}
