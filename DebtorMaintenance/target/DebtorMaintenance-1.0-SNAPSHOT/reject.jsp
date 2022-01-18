<%@ page import="project.bfour.debtormaintenance.dao.AdminDao" %>

<%
    AdminDao adminDao = new AdminDao();
    adminDao.reject(request.getParameter("txnId"), request.getParameter("rejectReason"));

    response.sendRedirect("/admin/debtor/details");
%>