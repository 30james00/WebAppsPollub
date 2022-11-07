<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mstolarz.lab3.CountryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
<% @SuppressWarnings("unchecked") ArrayList<CountryBean> countryList = (ArrayList<CountryBean>) session.getAttribute("list");
    for (CountryBean country : countryList) { %>
<p><%= country.getCode()%>
</p>
<p><%= country.getName()%>
</p>
<p><%= country.getPopulation()%>
</p>
<%
    }
%>
</body>
</html>
