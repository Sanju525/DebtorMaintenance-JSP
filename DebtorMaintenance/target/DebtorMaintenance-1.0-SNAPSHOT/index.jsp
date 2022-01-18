<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Debtor Maintenance</title>
  <jsp:include page="include/head_tag.html"/>
  <style>
  <jsp:include page="include/styles.css"/>
  </style>

  <script type="text/javascript">
    function alertFunc(msg) {
      window.alert(msg);
    }
  </script>
</head>
<body>


<jsp:include page="login.jsp"></jsp:include>


<div class="container">
<hr>
<span class="text-muted">Debtor Maintenance</span>
<br><br>
</div>
<jsp:include page="include/script_tag.html"/>
</body>
</html>
