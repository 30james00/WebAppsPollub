<%@ page import="com.mstolarz.lab3.CountryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% @SuppressWarnings("unchecked") CountryBean country = ((ArrayList<CountryBean>) session.getAttribute("list")).get(Integer.parseInt(request.getParameter("index"))); %>
<html>
<head>
    <title><%= country.getName()%>
    </title>
</head>
<body>
<h1><%= country.getName()%>
</h1>
<p>Code: <%= country.getCode()%>
<p>Population: <%= country.getPopulation()%>
</p>
<p>Surface area: <%= country.getSurfaceArea()%>
</p>
</body>
</html>
