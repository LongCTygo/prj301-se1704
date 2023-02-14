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
        <%@page import="dao.DAOCustomer,entity.Customer,java.util.Vector"%>
        <%
            DAOCustomer dao = new DAOCustomer();
            Vector<Customer> vector = dao.getAll("select * from customer");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>CustomerID</th>
                <th>Customer Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Address</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Customer cus: vector){%>
            <tr>
                <td></td>
    </body>
</html>
