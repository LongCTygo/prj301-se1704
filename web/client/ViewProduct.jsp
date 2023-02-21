<%-- 
    Document   : ViewProduct
    Created on : Feb 16, 2023, 8:05:09 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,display.ProductDisplay"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Product</title>
    </head>
    <body>
        <%
            Vector<ProductDisplay> vector = (Vector<ProductDisplay>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
        %>
        <div><%=title%></div>
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
            <% for (ProductDisplay temp : vector){%>
            <tr>
                <td><%=temp.getPid()%></td>
                <td><%=temp.getCate()%></td>
                <td><%=temp.getPname()%></td>
                <td><%=temp.getQuantity()%></td>
                <td><%=temp.getPrice()%></td>
                <td><img src="<%=temp.getImage()%>" width="100" height=150></td>
                <td><%=temp.getDescription()%></td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%></td>
                <td><a href="ProductController?go=update&id=<%=temp.getPid()%>&cateid=<%=temp.getCid()%>">Update</a></td>
                <td><a href="ProductController?go=delete&id=<%=temp.getPid()%>&cateid=<%=temp.getCid()%>">Delete</a></td>
            </tr>
            <%}%>
        </table>

    </body>
</html>
