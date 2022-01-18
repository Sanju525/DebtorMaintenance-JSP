<%@ page import="project.bfour.debtormaintenance.dao.AdminDao" %>

<%
    AdminDao adminDao = new AdminDao();
    adminDao.update(request.getParameter("txnId"));
    session.setAttribute("auth", "success");
    response.sendRedirect("/admin/debtor/details");
%>