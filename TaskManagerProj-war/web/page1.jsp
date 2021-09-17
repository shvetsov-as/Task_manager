<%-- 
    Document   : page1
    Created on : Jul 7, 2021, 10:43:39 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <form method="POST" action="Login">
            <input type="text" name="test" onsubmit="" maxlength="50" placeholder=" Сумма: <%
                String answerRandom1 = (String) request.getAttribute("answerRandom1");
                String answerRandom2 = (String) request.getAttribute("answerRandom2");
            if (answerRandom1 != null && answerRandom2 != null) {
        %>

<%=answerRandom1 + " и " + answerRandom2%>

        <% }%>" />
            <input type="submit" value="check" name="check" />
        </form>
    </body>
</html>
