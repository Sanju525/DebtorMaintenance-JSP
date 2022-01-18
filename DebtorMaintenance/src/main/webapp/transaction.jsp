<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include/head_tag.html"/>
    <title>Transaction Details</title>
    <style>
        body{
            background-color: hsla(89, 43%, 51%, 0.3);
        }
        .debtorform {
            width: 100%;
            max-width: 1000px;
            padding: 15px;
            margin: auto;
            border-radius: 10px;
            border-style: outset;
            background-color: whitesmoke;
        }
    </style>
</head>
<body>
<jsp:include page="include/debtor_nav.html"/>

<form class="debtorform shadow-lg bg-body rounded" action="${pageContext.request.contextPath}/debtor/form" method="post">
<%--    bank | debtorForm || transaction --%>
    <h4>Transaction Details</h4>
    <hr>
    <div class="row mb-3">
        <label for="transactionId" class="col-sm-2 col-form-label">Transaction ID</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionId" name="txnId"  value="<%= request.getAttribute("txnId")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionDateTime" class="col-sm-2 col-form-label">Date/Time</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionDateTime" name="txnDateTime"  value="<%= request.getAttribute("txnDateTime")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionStatus" class="col-sm-2 col-form-label">Status</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionStatus" name="transactionStatus"  value="<%= request.getAttribute("transactionStatus")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionInfo" class="col-sm-2 col-form-label">Information</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionInfo" name="transactionInfo"  value="<%= request.getAttribute("transactionInfo")%>" readonly>
        </div>
    </div>
    <hr>
    <h5>Confirm Details</h5>
    <hr>
    <h4>Personal Details</h4>
    <hr>
    <div class="row mb-3">
        <label for="debtorId" class="col-sm-2 col-form-label">DebtorID</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="debtorId" name="debtorId"  value="<%= session.getAttribute("uid")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" id="name" value="<%= request.getParameter("name")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="address1" class="col-sm-2 col-form-label">Address 1</label>
        <div class="col-sm-10">
            <input type="text" name="address1" class="form-control" id="address1" value="<%= request.getParameter("address1")%>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="address2" class="col-sm-2 col-form-label">Address 2</label>
        <div class="col-sm-10">
            <input type="text" name="address2" class="form-control" value="<%= request.getParameter("address2")%>" id="address2" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="fax" class="col-sm-2 col-form-label">Fax</label>
        <div class="col-sm-10">
            <input type="text" name="fax" class="form-control" value="<%= request.getParameter("fax") %>" id="fax" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="phone" class="col-sm-2 col-form-label">Phone</label>
        <div class="col-sm-10">
            <input type="number" name="phone" class="form-control" id="phone" value="<%= request.getParameter("phone") %>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
            <input type="email" name="email" class="form-control" id="email" value="<%= request.getParameter("email") %>" readonly>
        </div>
    </div>
    <h4>Bank Details</h4>
    <hr>
    <div class="row mb-3">
        <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="number" name="accountNumber" class="form-control" id="accountNumber" value="<%= request.getParameter("accountNumber") %>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="bankName" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name="bankName" class="form-control" id="bankName" value="<%= request.getParameter("bankName") %>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="branchName" class="col-sm-2 col-form-label">Branch Name</label>
        <div class="col-sm-10">
            <input type="text" name="branchName" class="form-control" id="branchName" value="<%= request.getParameter("branchName") %>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="ifsc" class="col-sm-2 col-form-label">IFSC</label>
        <div class="col-sm-10">
            <input type="text" name="ifsc" class="form-control" id="ifsc" value="<%= request.getParameter("ifsc") %>" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="currency" class="col-sm-2 col-form-label">Currency</label>
        <div class="col-sm-10">
            <input type="text" name="currency" class="form-control" id="currency" value="<%= request.getParameter("currency") %>" readonly>
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="button" class="btn btn-secondary" onclick="history.back()">Back</button>
    <button type="button" class="btn btn-alert" onclick="myFunc()">Cancel</button>

</form>
<script>
    function myFunc() {
        window.location.href = "/debtor/form";
    }
</script>
<jsp:include page="include/script_tag.html"/>
</body>
</html>
