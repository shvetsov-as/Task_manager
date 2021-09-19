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
          <input type="submit" value="Список всех пользователей" name="allUsers" />  
        </form>
        <br>
        <br>
        <p>Меню работы с учетными записями пользователей:</p>
        <br>
        <form method="GET" action="AdminServlet">
          <input type="submit" value="Создать запись" name="createUser" />  
        </form>
        <br>
        <br>
        <form method="GET" action="AdminServlet">
          <input type="submit" value="Редактировать запись" name="updateUser" />  
        </form>
        <br>
        <br>

        <br>
        
        
        <form method="GET" action="LogoutServlet">
          <input type="submit" value="Выйти" name="logout" />  
        </form>
    </body>
</html>
