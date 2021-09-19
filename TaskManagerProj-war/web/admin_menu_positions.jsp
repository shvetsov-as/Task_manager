<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.Positions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Admin </title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h3>Admin menu positions</h3>


        <form method="GET" action="AdminPositionServlet">
            <input type="submit" value="Все должности" name="allPositions" />
        </form>
        <br>
        <%
            List<Positions> answer = (List<Positions>) request.getAttribute("answer");
            if (answer != null) {
                String res = null;
                for (Positions p : answer) {
                    res = p.toHtmlString();%>
        <%=res%>
        <%}%>
        <%}%>
        <br>


        <form method="GET" action="AdminPositionServlet">
            <table border="1" width="300" height="50" cellspacing="1" cellpadding="1">
                <thead align="center">
                <td>Форма создания новой должности</td>
                </thead>
            </table>
            <table border="1" width="300" height="50" cellspacing="0" cellpadding="1">
                <tbody>
                    <tr>
                        <td align="center" width="150">Новое наименование должности</td>
                        <td align="center" width="150">Код редактируемой должности</td>
                    </tr>
                    <tr>
                        <td align="center" width="150">
                            <input type="text" name="posName" value="" placeholder="Начальник центра" minlength="1" maxlength="250"/>
                        </td>
                        

                            

                       <%-- <jsp:useBean id="PositionsFacade" class="dalSessionBean.PositionsFacade" scope="application"/>
                         
                        
                                <option>---</option>

                                <%
                                    List<Positions> posID = PositionsFacade.findPosID();

                                    String optionID = "";
                                    for (Positions p : posID) {
                                        optionID = p.getPositionId().toString();
                                %>    
                                <option><%=optionID%></option>
                                <%
                                    }
                                %>--%>


                    </tr>
                    <tr>
                        <td align="center" width="150">
                            <input type="reset" value="Очистить форму" name="posReset" />
                        </td>
                        <td align="center" width="150">
                            <input type="submit" value="Создать/Изменить" name="posCreateUpdate" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>



        <%--
          <jsp:useBean id="ReadUserBean" scope="page" class="bll_user.ReadUserBean"/>
          
          
    <% List<Users> result = new ArrayList<Users>(); 
          result = ReadUserBean.findAllusers();
          %>
        --%>

        <%@include file="WEB-INF/jspf/footer.jspf" %>

    </body>
</html>
