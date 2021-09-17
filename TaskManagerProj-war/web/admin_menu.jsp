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
        
        <h3>Admin menu page</h3>
        
        <br>
        <br>
        <form method="GET" action="AdminServlet">
          <input type="submit" value="Список всех пользователей" name="test" />  
        </form>
        <br>
        <br>
        <p> <a href="admin_menu_create.jsp">Создать нового пользователя</a>      </p>
        <p> <a href="admin_menu_update.jsp">Редактировать данные пользователей</a>      </p>
        
        
        
        
        
        <form method="GET" action="LogoutServlet">
          <input type="submit" value="Выйти" name="logout" />  
        </form>
    </body>
</html>
