package project.bfour.debtormaintenance.controller.debtor;

import project.bfour.debtormaintenance.customexceptions.UnauthException;
import project.bfour.debtormaintenance.dao.DebtorDao;
import project.bfour.debtormaintenance.dao.MySqlConn;
import project.bfour.debtormaintenance.model.BankAccount;
import project.bfour.debtormaintenance.model.DebtorForm;
import project.bfour.debtormaintenance.model.Transaction;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DebtorFormServlet", value = "/debtor/form")
public class DebtorFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("who")!= null &&session.getAttribute("who").equals("deb")) {
            request.getRequestDispatcher("/debtor_form.jsp").include(request, response);
        } else {
            throw new UnauthException();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String debtorId = request.getParameter("debtorId");
        String name = request.getParameter("name");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        long fax = Long.parseLong(request.getParameter("fax"));
        long phone = Long.parseLong(request.getParameter("phone"));
        String email = request.getParameter("email");
        long accountNumber = Long.parseLong(request.getParameter("accountNumber"));
        String bankName = request.getParameter("bankName");
        String branchName = request.getParameter("branchName");
        String ifsc = request.getParameter("ifsc");
        String currency = request.getParameter("currency");
        String txnId = request.getParameter("txnId");
        String txnDateTime = request.getParameter("txnDateTime");
        String txnStatus = request.getParameter("transactionStatus");
        String txnInfo = request.getParameter("transactionInfo");

        DebtorForm debtorForm = new DebtorForm(debtorId, name, address1, address2, fax, phone, email);
        BankAccount bankAccount = new BankAccount(debtorId, accountNumber, bankName, branchName, ifsc, currency);
        Transaction transaction = new Transaction(debtorId, txnId, txnDateTime, txnStatus, txnInfo);

        try {
            boolean status = DebtorDao.insertForm(MySqlConn.getCon(), debtorForm, bankAccount, transaction);
            if (status) { // true
                response.sendRedirect("/debtor/home");
            } else {
                response.sendRedirect("/logout");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
