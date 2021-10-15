<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.Employee"%>
<%@page import="dal.EmpJoinTask"%>
<%@page import="dal.Task_type"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Manager </title>
    </head>
    <body>
        <jsp:useBean id="bufBean" scope="page" class="dalSessionBean.BufBean" />
        <%@include file="WEB-INF/jspf/header.jspf" %>

        <h3>Manager create page</h3>

        <br>
        <p>Все задачи в базе</p>
        <br>

        <form method="GET" action="ManagerCreateServlet">
            <table border="1" width="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>
                            <input type="submit" value="Показать" name="showAll" />
                        </td>
                        <td>
                            <input type="submit" value="Скрыть" name="hideAll"/>
                        </td>
                    </tr>
                </tbody>
            </table> 
        </form>

        <%
            String showAlltask = (String) request.getAttribute("showAlltask");
            if (showAlltask != null) {
        %>

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
            <tbody>
                <%List<EmpJoinTask> listReadTask = bufBean.allTasks();
                    String tasks;
                    for (EmpJoinTask ejt : listReadTask) {

                        tasks = ejt.toHtmlStringTABLE();
                %>
                <%=tasks%>
                <%}%>
            </tbody>
        </table>
        <%}%>
        <br>
        <br>
        <p>Меню создания новой задачи</p>
        <br>


        <form method="GET" action="ManagerCreateServlet">
            <table border="1">
                <thead>
                    <tr>
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
                <tbody>
                    <tr>
                        <td>
                            <input type="text" name="taskName" value="" placeholder="Вх. № 31/12 - 4" required maxlength="50"/>
                        </td>
                        <td>
                            <select name="taskType" required>
                                <option></option>
                                <option><%=Task_type.OBI_SAVZ.getRusName()%></option>
                                <option><%=Task_type.OBI_TZI.getRusName()%></option>
                                <option><%=Task_type.OBI_ZI.getRusName()%></option>
                                <option><%=Task_type.REGIME.getRusName()%></option>
                                <option><%=Task_type.SO.getRusName()%></option>
                                <option><%=Task_type.TSS.getRusName()%></option>
                            </select>
                        </td>
                        <td>
                            <input  type="date" name="dateFrom" value=""
                                    max="2100-01-01" min="1970-01-01" required>
                        </td>
                        <td>
                            <input  type="date" name="dateTo" value=""
                                    max="2100-01-01" min="1970-01-01" required>
                        </td>
                        <td>
                            <input type="text" name="taskToDo" value="" placeholder="до 1200 символов" maxlength="1200"/>
                        </td>
                        <td>
                            <input type="text" name="taskNote" value="" placeholder="до 1200 символов" maxlength="1200"/>
                        </td>
                        <td>
                            <select name="employee" required>
                                <option></option>
                                <%List<Employee> listEmployee = bufBean.allEmployee();
                                    String empOption;
                                    for (Employee e : listEmployee) {
                                        empOption = e.toHtmlStringTABLEbutton();
                                %>
                                <%=empOption%>
                                <%}%>
                            </select>
                        </td>
                        <td>
                            <select name="status">
                                <option>Выполнено</option>
                                <option>Не выполнено</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <table border="1" width="1" cellspacing="1" cellpadding="1">
                <tbody>
                    <tr>
                        <td>
                            <input type="submit" value="Создать" name="create" />
                        </td>
                        <td>
                            <input type="reset" value="Очистить" />
                        </td>
                    </tr>
                </tbody>
            </table>             
        </form>
        <br>


        <%
            String answerCreateServ = (String) request.getAttribute("answerCreateServ");
            if (answerCreateServ != null) {
        %>
        <%=answerCreateServ%>
        <%}%>

        <%@include file="WEB-INF/jspf/footer_manager.jspf" %>



    </body>
</html>
