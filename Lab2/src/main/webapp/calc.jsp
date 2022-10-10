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
<%!
    public String calculate(ServletRequest request) {
        String amount = request.getParameter("amount");
        String rate = request.getParameter("rate");
        String instalments = request.getParameter("instalments");

        double amountDouble;
        double rateDouble;
        double instalmentsDouble;

        if (amount != null && !amount.trim().equals("") &&
                rate != null && !rate.trim().equals("") &&
                instalments != null && !instalments.trim().equals("")) {
            try {
                amountDouble = Double.parseDouble(amount);
                rateDouble = Double.parseDouble(rate);
                instalmentsDouble = Double.parseDouble(instalments);
            } catch (Exception ex) {
                return "Invalid input";
            }
            DecimalFormat df = new DecimalFormat("#.00");
            return "Monthly instalment: " + df.format(amountDouble * rateDouble / 12 / (1 - 1 / Math.pow(1 + rateDouble / 12, instalmentsDouble)));
        } else return "";
    }
%>
<p><%= calculate(request) %>
</p>

</body>
</html>
