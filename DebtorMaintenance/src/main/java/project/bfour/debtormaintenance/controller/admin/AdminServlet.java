package project.bfour.debtormaintenance.controller.admin;

import project.bfour.debtormaintenance.customexceptions.UnauthException;
import project.bfour.debtormaintenance.dao.AdminDao;
import project.bfour.debtormaintenance.dao.DebtorDao;
import project.bfour.debtormaintenance.dao.MySqlConn;
import project.bfour.debtormaintenance.model.Transaction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet", value = "/admin/debtor/details")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("who")!= null && session.getAttribute("who").equals("adm")){
            try {
                request.setAttribute("debtorList", DebtorDao.getDebtorDetails(MySqlConn.getCon()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/all_debtor_details.jsp").include(request, response);
        } else {
            throw new UnauthException();
        }
    }
}
