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
        <%@page import="dao.DAOAdmin,entity.Admin,java.util.Vector"%>
        <%
            DAOAdmin dao = new DAOAdmin();
            Vector<Admin> vector = dao.getAll("select * from admin");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>Admin</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Admin temp: vector){%>
            <tr>
                <td><%=temp.getAdmin()%></td>
                <td><a href="ViewAdmin.jsp?go=update&id=<%=temp.getAdmin()%>">Update</a></td>
                <td><a href="ViewAdmin.jsp?go=delete&id=<%=temp.getAdmin()%>">Delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
