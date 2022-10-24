<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>Invalid data input!</h2>
<p><b>Error occurred:</b><br>
    <%= exception.getMessage() %>. <br/>
</p>
</body>
</html>