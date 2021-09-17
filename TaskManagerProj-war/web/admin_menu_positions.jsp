<%-- 
    Document   : Admin_menu
    Created on : Sep 11, 2021, 4:30:48 PM
    Author     : User
--%>

<%@page import="dal.Positions"%>
<%@page import="dal.Users"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <%
            List <Positions> answer = (List <Positions>) request.getAttribute("answer");

            if (answer != null) {
                String res = null;
                for (Positions p : answer){
                    res = p.toHtmlString();%>
                    <%=res%>
                <%}%>
        
        
        <%}%>
        
      <%--
        <jsp:useBean id="ReadUserBean" scope="page" class="bll_user.ReadUserBean"/>
        
        
<% List<Users> result = new ArrayList<Users>(); 
        result = ReadUserBean.findAllusers();
        %>
        --%>
        
       <%@include file="WEB-INF/jspf/footer.jspf" %>

    </body>
</html>
