<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.Users"%>
<%@page import="dal.Positions"%>
<%@page import="java.util.List"%>
<%@page import="dal.UserJoinThree"%>
<%@page import="dal.Role"%>
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

        <br>
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

            <%
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", 0);
                //response.setIntHeader("Refresh", 2);

                List<UserJoinThree> listReadUser = bufBean.userJoinThree();
                String users;
                for (UserJoinThree us : listReadUser) {

                    users = us.toHtmlStringTABLE();
            %>
            <%=users%>
            <%}%>    
        </table>
        <br>
        <br>

        <table border="1">
            <thead>
                <tr>
                    <th>userId</th>
                    <th>userLogin</th>
                    <th>userRole</th>
                    <th>userPasswd</th>
                    <th>userMark</th>
                    <th>empId</th>
                    <th>userIdUsers</th>
                    <th>empSurname</th>
                    <th>empName</th>
                    <th>empMidName</th>
                    <th>posIdPosition</th>
                    <th>positionId</th>
                    <th>position</th>
                </tr>
            </thead>

            <%
                for (UserJoinThree us : listReadUser) {

                    users = us.toHtmlStringTABLEtest();
            %>
            <%=users%>
            <%}%>    
        </table>
        <br>
        <br>




        <form method="POST" action="AdminUpdateServlet">

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>

                    <tr>
                        <td>Выберите ID пользователя</td>
                        <td>
                            <select name="userId" required>
                                <option></option>
                                <%List<Users> listUsers = bufBean.findAllusers();
                                    String userIdOption;
                                    for (Users u : listUsers) {
                                        userIdOption = u.toHtmlStringBUTTONid();
                                %>
                                <%=userIdOption%>
                                <%}%>
                            </select>
                        </td>
                        <td>Запись с данным ID можно ОБНОВИТЬ или УДАЛИТЬ</td>
                    </tr>


                    <tr>
                        <td>Введите новый логин</td>
                        <td>
                            <input type="text" name="login" value="" placeholder="ПетровАС"/>
                        </td>
                        <td>Значение логина должно быть уникальным</td>
                    </tr>
                    <tr>
                        <td>Введите новый пароль</td>
                        <td>
                            <input type="password" name="passwd1" value="" placeholder="aA345678"/>
                        </td>
                        <td>Пароль содержит 8 символов: строчный, прописной, цифру</td>
                    </tr>
                    <tr>
                        <td>Повторите пароль</td>
                        <td>
                            <input type="password" name="passwd2" value="" placeholder="aA345678"/>
                        </td>
                        <td>Пароли должны совпадать</td>
                    </tr>
                    <tr>
                        <td>Выберите новую роль пользователя</td>
                        <td>
                            <select name="role">
                                <option></option>
                                <option><%=Role.ADMIN.getRusName()%></option>
                                <option><%=Role.MANAGER.getRusName()%></option>
                                <option><%=Role.USER.getRusName()%></option>
                            </select>
                        </td>
                        <td>Необязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите новую фамилию</td>
                        <td>
                            <input type="text" name="surname" value="" placeholder="Петров"/>
                        </td>
                        <td>Необязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите новое имя</td>
                        <td>
                            <input type="text" name="name" value="" placeholder="Александр"/>
                        </td>
                        <td>Необязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите новое отчество</td>
                        <td>
                            <input type="text" name="midname" value="" placeholder="Сергеевич"/>
                        </td>
                        <td>Необязательное поле</td>
                    </tr>
                    <tr>
                        <td>Выберите новую должность</td>
                        <td>
                            <select name="position">
                                <option></option>
                                <%List<Positions> listPosition = bufBean.allPositions();
                                    String positionOption;
                                    for (Positions pos : listPosition) {
                                        positionOption = pos.toHtmlStringBUTTONname();
                                %>
                                <%=positionOption%>
                                <%}%>
                            </select>
                        </td>
                        <td>Необязательное поле</td>
                    </tr>
                    
                    <tr>
                        
                    </tr>
                    
                    <tr>
                        <td> 
                            <input type="submit" value="Обновить" name="update" /> 
                        </td>
                        <td>
                            <input type="reset"  value="Очистить" name="reset"/>
                        </td>
                        <td>
                            <input type="submit" value="Удалить" name="delete" />
                        </td>
                    </tr>
                </tbody>
            </table>

            <%
                String answerUpdateServ = (String) request.getAttribute("answerUpdateServ");
                if (answerUpdateServ != null) {
            %>
            <%=answerUpdateServ%>
            <%}%>

    </form>

    <form method="GET" action="AdminServlet">
        <input type="submit" value="Создать новую должность" name="newPosition" />
    </form>

    <%@include file="WEB-INF/jspf/footer_admin.jspf" %>
</body>
</html>
