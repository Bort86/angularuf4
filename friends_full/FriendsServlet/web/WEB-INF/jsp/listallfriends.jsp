<%-- 
    Document   : listallfriends
    Created on : 11-sep-2016, 13:03:46
    Author     : ProvenSoft
--%>

<%@page import="java.util.List"%>
<%@page import="proven.friends.model.Friend"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friend list</title>
        <link type="text/css" rel="stylesheet" href="/css/styles.css">
        <style type="text/css">
            table * {
                border: solid thin blue;
            }
        </style>
    </head>
    <body>
        <h1>List of friends</h1>
        <%
            List<Friend> data = ( List<Friend>) request.getAttribute("friends");
            out.println("<table>");
            out.println("<tr>");
                out.println(String.format("<th>%s</th>", "phone"));
                out.println(String.format("<th>%s</th>", "name"));
                out.println(String.format("<th>%s</th>", "age"));
            out.println("</tr>");            
            for (Friend f: data) {
                out.println("<tr>");
                out.println(String.format("<td>%s</td>", f.getPhone()));
                out.println(String.format("<td>%s</td>", f.getName()));
                out.println(String.format("<td>%d</td>", f.getAge()));
                out.println("</tr>");
            }
            out.println("</table>");
        %>
    </body>
</html>
