<%-- 
    Document   : ViewProduct
    Created on : Feb 14, 2023, 11:48:28 AM
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
        <%@page import="dao.DAOProduct,entity.Product,java.util.Vector,java.sql.ResultSet"%>
        <%
            DAOProduct dao = new DAOProduct();
            ResultSet rs = dao.getData("select * from Product as a join Category as b on a.cateID = b.cateID");
        %>
        <table border=\"1\">
            <caption>Customer List</caption>
            <tr>
                <th>Product ID</th>
                <th>Category</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%while(rs.next()){%>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(10)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getString(4)%></td>
                <td><img src="<%=rs.getString(5)%>" width="100" height=150></td>
                <td><%=rs.getString(6)%></td>
                <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                <td><a href="ViewProduct.jsp?go=delete&id=<%=rs.getString(1)%>">Update</a></td>
                <td><a href="ViewProduct.jsp?go=delete&id=<%=rs.getString(1)%>">Delete</a></td>
            </tr>
            <%}%>
            </table>
    </body>
</html>
