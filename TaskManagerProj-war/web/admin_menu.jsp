<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.Employee"%>
<%@page import="dal.UserJoinThree"%>
<%@page import="dal.Users"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>Администратор</title>
    </head>

    <%@include file="WEB-INF/jspf/header.jspf" %>
    <%--<%session = request.getSession();
    if(session.isNew()||session == null){
        response.sendRedirect("index.jsp");
}%>--%>
    <h3>Администратор. Главное меню.</h3>

    <br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>ЛОГИН</th>
                <th>РОЛЬ</th>
                <th>ФАМИЛИЯ</th>
                <th>ИМЯ</th>
                <th>ОТЧЕСТВО</th>
                <th>ДОЛЖНОСТЬ</th>
            </tr>
        </thead>

        <jsp:useBean id="bufBean" scope="page" class="dalSessionBean.BufBean" />

        <%List<UserJoinThree> listReadUser = bufBean.userJoinThree();
            String users;
            for (UserJoinThree us : listReadUser) {

                users = us.toHtmlStringTABLE();
        %>
        <%=users%>
        <%}%>  
    </table>    
    <br>
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

    <%-- 
    <br>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>ЛОГИН</th>
                <th>РОЛЬ</th>
                <th>ФАМИЛИЯ</th>
                <th>ИМЯ</th>
                <th>ОТЧЕСТВО</th>
                <th>ДОЛЖНОСТЬ</th>
            </tr>
        </thead>

        <% List<String> usersEmpList = bufBean.userJoin();
            String userJoin;
            for (String s : usersEmpList) {

                userJoin = s;
        %>
        <%=userJoin%>
        <%}%>
    </table>    
    <br>--%>
</html>
