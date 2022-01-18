package project.bfour.debtormaintenance.controller.admin;

import project.bfour.debtormaintenance.customexceptions.UnauthException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = "/admin/home")
public class AdminHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("who").equals("adm")){
            getServletContext().getRequestDispatcher("/admin_home.jsp").include(request, response);
        } else {
            throw new UnauthException();
        }
    }
}
