<%-- 
    Document   : listallfriends
    Created on : 11-sep-2016, 13:03:46
    Author     : ProvenSoft
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="proven.friends.model.Friend"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friend list</title>
        <style type="text/css">
            table td {
                border: solid thin blue;
            }
        </style>
    </head>
    <body>
        <h1>List of friends</h1>
        <table>
        <c:forEach var="elem" items="${friends}">
            <tr>
                <td><c:out value="${elem.phone}"/></td>
                <td><c:out value="${elem.name}"/></td>
                <td><c:out value="${elem.age}"/></td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
