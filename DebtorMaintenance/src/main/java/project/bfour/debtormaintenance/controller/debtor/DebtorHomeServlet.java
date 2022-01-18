package project.bfour.debtormaintenance.controller.debtor;

import project.bfour.debtormaintenance.customexceptions.UnauthException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DebtorHomeServlet", value = "/debtor/home")
public class DebtorHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("who")!= null && session.getAttribute("who").equals("deb")) {
            request.getRequestDispatcher("/debtor_home.jsp").include(request, response);
        } else {
            throw new UnauthException();
        }
    }
}
