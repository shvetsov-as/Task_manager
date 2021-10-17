<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.EmpJoinTask"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> User </title>
    </head>
    <body>
        
        <h3>User menu page</h3>
        
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <jsp:useBean id="bufBean" scope="page" class="dalSessionBean.BufBean" />
        <br>
        <h4>СПИСОК АКТИВНЫХ ЗАДАЧ</h4>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>НАИМЕНОВАНИЕ</th>
                    <th>ТИП</th>
                    <th>ДАТА ПОСТУПЛЕНИЯ</th>
                    <th>ДАТА ИСПОЛНЕНИЯ</th>
                    <th>ЗАДАЧИ К ИСПОЛНЕНИЮ</th>
                    <th>ПРИМЕЧАНИЕ</th>
                    <th>ИСПОЛНИТЕЛЬ</th>
                    <th>СТАТУС</th>
                </tr>
            </thead>
            <%List<EmpJoinTask> listReadTask = bufBean.allIncompTasks();
                String tasks;
                for (EmpJoinTask ejt : listReadTask) {

                    tasks = ejt.toHtmlStringTABLE();
            %>
            <%=tasks%>
            <%}%>  
        </table>    
        <br>
        <br>
        <br>
        <p>Меню работы с задачами:</p>
        <br>
        <form method="GET" action="UserServlet">
            <input type="submit" value="Найти запись" name="findTask" />  
        </form>
        <br>
        <br>
        
        <form method="GET" action="UserServlet">
            <input type="submit" value="Редактировать запись" name="updateTask" />  
        </form>
        <br>
        <br>

        <form method="GET" action="LogoutServlet">
            <input type="submit" value="Выйти" name="logout" />  
        </form>

    </body>
</html>
