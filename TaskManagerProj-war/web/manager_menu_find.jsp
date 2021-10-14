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
        <title> Manager </title>
    </head>
    <body>

        <%@include file="WEB-INF/jspf/header.jspf" %>

        <h3>Manager find page</h3>

        <br>
        
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

            <jsp:useBean id="bufBean" scope="page" class="dalSessionBean.BufBean" />

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
        <p>Меню поиска по задачам:</p>
        <br>
        


        <%@include file="WEB-INF/jspf/footer_manager.jspf" %>



    </body>
</html>
