<%-- 
    Document   : success_op
    Created on : Oct 6, 2021, 7:48:03 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body>
        <%@include file="jspf/header.jspf" %>
      
        <table border="1" width="1" cellspacing="1" cellpadding="1">

            <h3>Success update page</h3>
            <br>
            <tbody>
                <tr>
                    <td>Операция выполнена успешно</td>
                </tr>
                <tr>
                    <td>
                        <form method="GET" action="AdminUpdateServlet">
                            <input type="submit" value="Обновить" name="restart" />  
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>

        
    </body>
</html>
