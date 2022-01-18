package project.bfour.debtormaintenance.controller.debtor;

import project.bfour.debtormaintenance.customexceptions.UnauthException;
import project.bfour.debtormaintenance.dao.DebtorDao;
import project.bfour.debtormaintenance.dao.MySqlConn;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DebtorUpdatesServlet", value = "/debtor/updates")
public class DebtorUpdatesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        get detailsForm by uid
        HttpSession session = request.getSession();
        if (session.getAttribute("who")!= null && session.getAttribute("who").equals("deb")) {
            String uid = (String) session.getAttribute("uid");
            try {
                request.setAttribute("debtorList", DebtorDao.getDebtorDetails(MySqlConn.getCon(), uid));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/debtor_updates.jsp").include(request, response);
        } else {
            throw new UnauthException();
        }
    }
}
