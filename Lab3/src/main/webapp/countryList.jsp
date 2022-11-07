<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mstolarz.lab3.CountryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
<% @SuppressWarnings("unchecked") ArrayList<CountryBean> countryList = (ArrayList<CountryBean>) session.getAttribute("list");
    for (int i = 0; i < countryList.size(); i++) {
        CountryBean countryBean = countryList.get(i);
%>
<b><%= countryBean.getName()%></b>
<%= countryBean.getCode()%>
<%= countryBean.getPopulation()%>
<a href="details.jsp?index=<%= i%>">Details</a>
<br>
<%
    }
%>
</body>
</html>
