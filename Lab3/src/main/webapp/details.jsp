<%@ page import="com.mstolarz.lab3.CountryBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% if (session.getAttribute("list") == null) { %>
<head>
    <title>Session error</title>
</head>
<body>
<p>Session not initialized</p>
</body>
<% } else {
    @SuppressWarnings("unchecked") CountryBean country = ((ArrayList<CountryBean>) session.getAttribute("list")).get(Integer.parseInt(request.getParameter("index"))); %>
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
<% } %>
</body>
</html>
