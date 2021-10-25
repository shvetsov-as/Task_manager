<%-- 
    Document   : index
    Created on : Jul 7, 2021, 10:43:26 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход</title> 
    </head>
    <body>
        
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <form method="POST" action="InputCheck">
            <h5>Имя пользователя</h5>
            <input type="text" name="login" placeholder="IvanovAN" value="" maxlength="50"/>
            <h5>Пароль</h5>
            <input type="password" name="pass" placeholder="aA345678" value="" maxlength="250"/>
            <input type="submit" value="Войти" name="submit" />
        </form>
        <%
            String answerInputCheck = (String) request.getAttribute("answerInputCheck");

            if (answerInputCheck != null) {
        %>
        <%=answerInputCheck%>
        <%}%>
    </body>
</html>
