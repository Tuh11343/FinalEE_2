<%-- 
    Document   : TestJSP
    Created on : Oct 14, 2023, 6:56:19 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/ItemServlet" method="post">
            <h1>Item Type List</h1>
            <table>
                <tr>
                    <th>Name</th>
                </tr>
                <c:forEach items="${itemTypeList}" var="itemType">
                    <tr>
                        <td>${itemType.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
