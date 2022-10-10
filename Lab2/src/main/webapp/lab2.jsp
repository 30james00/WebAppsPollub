<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lab 2</title>
</head>
<body>
<h1>Lab 2</h1>
<%=new Date()%>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    String date = dateFormat.format(now);
%>
<br>
<%=date%>
</body>
</html>
