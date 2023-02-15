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
        <%@page import="dao.DAOBill,entity.Bill,java.util.Vector"%>
        <%
            DAOBill dao = new DAOBill();
            Vector<Bill> vector = dao.getAll("select * from Bill");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>BillID</th>
                <th>Date Create</th>
                <th>Rec Address</th>
                <th>Rec Phone</th>
                <th>Note</th>
                <th>Total Money</th>
                <th>Status</th>
                <th>CustomerID</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (Bill temp: vector){%>
            <tr>
                <td><%=temp.getBid()%></td>
                <td><%=temp.getDateCreate()%></td>
                <td><%=temp.getRecAddress()%></td>
                <td><%=temp.getRecPhone()%></td>
                <td><%=temp.getNote()%></td>
                <td><%=temp.getRecPhone()%></td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%></td>
                <td><%=temp.getCid()%></td>
                <td><a href="ViewBill.jsp?go=update&id=<%=temp.getBid()%>">Update</a></td>
                <td><a href="ViewBill.jsp?go=delete&id=<%=temp.getBid()%>">Delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
