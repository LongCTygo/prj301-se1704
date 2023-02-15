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
        <%@page import="dao.DAOBillDetail,entity.BillDetail,java.util.Vector"%>
        <%
            DAOBillDetail dao = new DAOBillDetail();
            Vector<BillDetail> vector = dao.getAll("select * from BillDetail");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>BillID</th>
                <th>ProductID</th>
                <th>Buy Quantity</th>
                <th>Buy Price</th>
                <th>Subtotal</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%for (BillDetail temp: vector){%>
            <tr>
                <td><%=temp.getBid()%></td>
                <td><%=temp.getPid()%></td>
                <td><%=temp.getBuyQuantity()%></td>
                <td><%=temp.getBuyPrice()%></td>
                <td><%=temp.getSubtotal()%></td>
                <td><a href="ViewBillDetail.jsp?go=update&bid=<%=temp.getBid()%>&pid=<%=temp.getPid()%>">Update</a></td>
                <td><a href="ViewBillDetail.jsp?go=delete&bid=<%=temp.getBid()%>&pid=<%=temp.getPid()%>">Delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
