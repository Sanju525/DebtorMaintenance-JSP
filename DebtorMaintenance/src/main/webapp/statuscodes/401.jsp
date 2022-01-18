<%--
  Created by IntelliJ IDEA.
  User: sanju
  Date: 03/01/22
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 401</title>
</head>
<body>
<div class="mx-auto">
    <h1>401 Unauthorized</h1>

    <h4>You are not allowed to this page!</h4>

    <p>Please Login <a href="${pageContext.request.contextPath}/">Debtor Login</a>
        or <a href="${pageContext.request.contextPath}/admin/login">Admin Login</a></p>

</div>

</body>
</html>
