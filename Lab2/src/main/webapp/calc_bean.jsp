<%@ page import="com.mstolarz.lab2.TextHelper" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="loan" class="com.mstolarz.lab2.LoanBean" scope="session"/>
<jsp:setProperty name="loan" property="*"/>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Loan calculator</title>
</head>
<body>
<h1>Loan calculator</h1>
<form action="" id="form">
    <input type="hidden" name="calculate" value="yes">
    <label>
        Loan amount:
        <input type="text" name="amount" value="<%=loan.getAmount()%>">
    </label>
    <label>
        Interest Rate per annum:
        <input type="text" name="rate" value="<%=loan.getRate()%>">
    </label>
    <label>
        Instalments:
        <input type="number" name="instalments" value="<%=loan.getInstalments()%>">
    </label>
    <input type="submit" value="Calculate">
</form>
<%if (!TextHelper.isEmptyString(new String[]{loan.getLoan()})) { %>
<p>
    <b>Loan instalment:</b>
    <jsp:getProperty name="loan" property="loan"/>
</p>
<% } %>
</body>
</html>
