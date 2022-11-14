<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 11/14/22
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ErrorPage</title>
</head>
<body>
<h1>Error Page</h1>
<p>Failed URL: ${url}<br>
    Exception: ${exception.message} <br>
    <c:forEach items="${exception.stackTrace}" var="ste">
        ${ste}
        <br>
    </c:forEach>
</p>
</body>
</html>
