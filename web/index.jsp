<%@page import="web.DbListener"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <%if(DbListener.exceptionMessage != null){%>
         <h3 style="color:red"><%= DbListener.exceptionMessage %></h3>
        <%}%>
    </body>
    <hr/>
</html>
