<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="include/head_tag.html"/>
    <title>Debtor Home</title>
    <style>
        body{
            background-color:hsla(89, 43%, 51%, 0.3);
        }
    </style>
</head>
<body>
<jsp:include page="include/debtor_nav.html"/>
<div class="container">
    <p>Username : <strong><%= session.getAttribute("username")%></strong></p>
    <p>Debtor ID: <strong><%= session.getAttribute("uid")%></strong></p>
</div>


<jsp:include page="include/script_tag.html"/>
</body>
</html>
