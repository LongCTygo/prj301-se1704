<%-- 
    Document   : menu
    Created on : Feb 21, 2023, 10:34:46 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Category"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Category> vectorCat = (Vector<Category>) request.getAttribute("dataMenu");
        %>
        <ul>
            <% for (Category cat : vectorCat){%>
            <li><a href="ClientController?go=display&cid=<%=cat.getCateId()%>"><%=cat.getCateName()%></a></li>
            <%}%>
        </ul>

    </body>
</html>
