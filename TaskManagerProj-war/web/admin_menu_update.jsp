<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Admin </title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h3>Admin menu update user</h3>
        
        
        <form method="GET" action="AdminUpdateServlet">
            <input type="submit" value="AdminUpdateServlet" name="test" />
        </form>
        
        
        <p> <a href="admin_menu_positions.jsp">Создать новую должность</a>      </p>
        
        
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
