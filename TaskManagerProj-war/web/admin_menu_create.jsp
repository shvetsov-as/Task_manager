<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.UserJoinThree"%>
<%@page import="dal.Positions"%>
<%@page import="dal.Users"%>
<%@page import="dal.Role"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Admin </title>
    </head>
    <body>
        <%%>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h3>Admin menu create user</h3>

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

        <form method="POST" action="AdminCreateServlet">

            <table border="1" cellspacing="1" cellpadding="1">

                <tbody>
                    <tr>
                        <td>Введите логин</td>
                        <td>
                            <input type="text" name="login" value="" placeholder="ПетровАС"/>
                        </td>
                        <td>Значение логина должно быть уникальным</td>
                    </tr>
                    <tr>
                        <td>Введите пароль</td>
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
                        <td>Выберите роль пользователя</td>
                        <td>
                            <select name="role">
                                <option>Роль</option>
                                <option><%=Role.ADMIN.getRusName()%></option>
                                <option><%=Role.MANAGER.getRusName()%></option>
                                <option><%=Role.USER.getRusName()%></option>
                            </select>
                        </td>
                        <td>Обязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите фамилию</td>
                        <td>
                            <input type="text" name="surname" value="" placeholder="Петров"/>
                        </td>
                        <td>Обязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите имя</td>
                        <td>
                            <input type="text" name="name" value="" placeholder="Александр"/>
                        </td>
                        <td>Обязательное поле</td>
                    </tr>
                    <tr>
                        <td>Введите отчество</td>
                        <td>
                            <input type="text" name="midname" value="" placeholder="Сергеевич"/>
                        </td>
                        <td>Обязательное поле</td>
                    </tr>
                    <tr>
                        <td>Выберите должность</td>
                        <td>
                            <select name="position">
                                <option>Должность</option>
                                <%List<Positions> listPosition = bufBean.allPositions();
                                    String positionOption;
                                    for (Positions pos : listPosition) {
                                        positionOption = pos.toHtmlStringBUTTONname();
                                %>
                                <%=positionOption%>
                                <%}%>
                            </select>
                        </td>
                        <td>Обязательное поле</td>
                    </tr>
                    
                    <tr> 
                    </tr>
                    
                    <tr>
                        <td> 
                            
                        </td>
                        <td>
                            <input type="submit" value="Создать" name="create" />
                        </td>
                        <td>
                            <input type="reset"  value="Очистить" name=""/>
                        </td>
                    </tr>
                </tbody>
            </table>

            <%
                String answerCreateServ = (String) request.getAttribute("answerCreateServ");
                if (answerCreateServ != null) {
            %>
            <%=answerCreateServ%>
            <%}%>

        </form>

        <br>
        <br>
        <form method="GET" action="AdminServlet">
            <input type="submit" value="Создать новую должность" name="newPosition" />
        </form>


        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
