<%@ page import="java.util.Objects" %>
<%@ page import="com.mstolarz.lab2.TextHelper" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
        <input type="text" name="amount">
    </label>
    <label>
        Interest Rate per annum:
        <input type="text" name="rate">
    </label>
    <label>
        Instalments:
        <input type="number" name="instalments">
    </label>
    <input type="submit" value="Calculate">
</form>
<%! public String calculateLoan(HttpServletRequest request) {
    if (Objects.equals(request.getParameter("calculate"), "yes")) {
        String amountText = request.getParameter("amount");
        String rateText = request.getParameter("rate");
        String instalmentsText = request.getParameter("instalments");
        String[] texts = {amountText, rateText, instalmentsText};

        if (TextHelper.isEmptyString(texts)) return "Missing value";

        amountText = TextHelper.changeCommaToDot(amountText);
        rateText = TextHelper.changeCommaToDot(rateText);
        instalmentsText = TextHelper.changeCommaToDot(instalmentsText);

        double amount;
        double rate;
        double instalments;

        try {
            amount = Double.parseDouble(amountText);
            rate = Double.parseDouble(rateText);
            instalments = Double.parseDouble(instalmentsText);
        } catch (NumberFormatException e) {
            return "Value is not a number";
        }

        double result = amount * rate / 12.0 / (1 - (1 / Math.pow(1 + rate / 12, instalments)));

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(result);
    }
    return "";
}
%>
<% String result = calculateLoan(request);
    if (!Objects.equals(result, "")) { %>
<p><%=result%>
</p>
<% } %>
</body>
</html>
