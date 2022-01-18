package project.bfour.debtormaintenance.controller.debtor;

import project.bfour.debtormaintenance.customexceptions.UnauthException;
import project.bfour.debtormaintenance.dao.DebtorDao;
import project.bfour.debtormaintenance.dao.MySqlConn;
import project.bfour.debtormaintenance.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "TransactionServlet", value = "/transaction")
public class TransactionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        throw new UnauthException();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("fromForm").equals("true")) {
            Transaction newTxn = null;
            try {
                newTxn = DebtorDao.getNewTxn(MySqlConn.getCon(), request.getParameter("debtorId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("txnId", newTxn.getId());
            request.setAttribute("txnDateTime", newTxn.getDateTime());
            request.setAttribute("transactionStatus", newTxn.getStatus());
            request.setAttribute("transactionInfo", newTxn.getInformation());
            getServletContext().getRequestDispatcher("/transaction.jsp").forward(request, response);

//            doGet(request, response);
        } else {
            response.sendRedirect("/debtor/home");
        }
    }

}
