<%@ page import="java.util.ArrayList" %>
<%@ page import="project.bfour.debtormaintenance.model.DetailsForm" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>All Details</title>
  <jsp:include page="include/head_tag.html"/>
  <style>
    body{
      background-color:hsla(89, 43%, 51%, 0.3);
    }
    p[about='P'] {
      color:#664d03;
      background-color:#fff3cd;
      border-color:#ffecb5
    }
    p[about="R"] {
      color:#842029;background-color:#f8d7da;border-color:#f5c2c7
    }
    p[about="A"] {
      color:#0f5132;
      background-color:#d1e7dd;
      border-color:#badbcc
    }
  </style>
  <script type="text/javascript">
    function alertFunc(msg) {
      window.alert(msg);
    }
  </script>
</head>
<body>
<jsp:include page="include/admin_nav.html"/>
<% if (session.getAttribute("auth") != null) { %>
<script type="text/javascript">
  alertFunc("Debtor Authorized Successfully!");
</script>
<% session.removeAttribute("auth"); %>
<%}%>

<div class="container">
  <%if (request.getAttribute("debtorList").toString().equals("[]")){%>
    <h2>No Pending Debtor Forms</h2>
  <%} else {%>
  <c:forEach items="${debtorList}" var="debtor">
    <div class="row row-cols-1 justify-content-evenly">
      <div class=" card col-8 shadow-lg bg-body rounded" style="padding: 0">
        <div class="card-header">
            <p id="txnId">${debtor.getTxnId()}</p>
        </div>
        <div class="card-body">
          <div class="row mb-3">
            <label for="debtorName" class="col-sm-5 col-form-label">Debtor Id</label>
            <div class="col-sm-7">
              <p class="form-control" id="debtorId" style="margin-bottom: 0">${debtor.getId()}</p>
            </div>
          </div>
          <div class="row mb-3">
            <label for="debtorName" class="col-sm-5 col-form-label">Debtor Name</label>
            <div class="col-sm-7">
              <p class="form-control" id="debtorName" style="margin-bottom: 0">${debtor.getName()}</p>
            </div>
          </div>
          <div class="row mb-3">
            <label for="debtorName" class="col-sm-5 col-form-label">Status</label>
            <div class="col-sm-7">
              <p class="form-control" id="status" about="${debtor.getStatus()}" style="margin-bottom: 0">${debtor.getStatus()}</p>
            </div>
          </div>
          <div class="row mb-3">
            <label for="debtorName" class="col-sm-5 col-form-label">Date</label>
            <div class="col-sm-7">
              <p class="form-control" id="date" style="margin-bottom: 0">${debtor.getDateTime()}</p>
            </div>
          </div>
          <div class="button text-center">
            <button class="btn btn-secondary" id="viewmore"><a class="link-light" href="${pageContext.request.contextPath}/view_debtor_details.jsp?txnId=${debtor.getTxnId()}">View Details</a></button>
            <button class="btn btn-success" id="authorize"><a class="link-light" href="/authorize.jsp?txnId=${debtor.getTxnId()}">Authorize</a></button>
            <button class="btn btn-danger" id="reject"><a class="link-light" href="/reject_form.jsp?txnId=${debtor.getTxnId()}">Reject</a></button>

          </div>
        </div>
      </div>
    </div><br>
  </c:forEach>

  <%}%>

  <hr>
  <span class="text-muted">Debtor Maintenance</span>
  <br><br>
</div>
<jsp:include page="include/script_tag.html"/>
</body>
</html>