<%--
  Created by IntelliJ IDEA.
  User: adonalsium
  Date: 16.04.18
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Szpinakov | Registration Success</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href='https://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../main.css">
</head>
<body>
<%
    String message = null;
    String sessionID = null;

    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("message")) message = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<h3>Registration Success</h3>
<h2>Welcome new user!</h2>
<h4><%=message%></h4>
<h4>Session ID = <%=sessionID%></h4>
<br><br>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>

