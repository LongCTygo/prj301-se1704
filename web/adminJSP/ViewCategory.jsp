<%-- 
    Document   : ViewCustomer
    Created on : Feb 14, 2023, 10:45:03 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page import="dao.DAOCategory,entity.Category,java.util.Vector"%>
        <%
            DAOCategory dao = new DAOCategory();
            Vector<Category> vector = dao.getAll("select * from Category");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>CategoryID</th>
                <th>CategoryName</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Category temp: vector){%>
            <tr>
                <td><%=temp.getCateId()%></td>
                <td><%=temp.getCateName()%></td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%></td>
                <td><a href="ViewCategory.jsp?go=update&bid=<%=temp.getCateId()%>">Update</a></td>
                <td><a href="ViewCategory.jsp?go=delete&bid=<%=temp.getCateId()%>">Delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
