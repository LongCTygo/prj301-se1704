<%-- 
    Document   : UpdateProduct
    Created on : Feb 16, 2023, 8:50:53 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Product,java.util.Vector,entity.Category"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int cid = (Integer) request.getAttribute("cateId");
            Product pro = (Product) request.getAttribute("data");
            Vector<Category> cateData = (Vector<Category>) request.getAttribute("cateData");
            System.out.println("-----");
            System.out.println(pro);
            System.out.println(cateData);
            System.out.println("-----");
        %>
        <form action="ProductController" method="post">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    Category: <select name="CateID">
                        <% for (Category temp : cateData){ %>
                    <option value=<%=temp.getCateId()%>  
                            <%=((temp.getCateId() == cid)?"selected":"")%>>
                        <%=temp.getCateName()%>
                    </option>
                        <%}%>
                    </select>
                </tr>
                <tr>
                    <td><label for="pid">Pid</label></td>
                    <td><input type="text" name="pid" id="pid" value="<%=pro.getPid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="pname">Pname</label></td>
                    <td><input type="text" name="pname" id="pname" value="<%=pro.getPname()%>"></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity" value="<%=pro.getQuantity()%>" ></td>
                </tr>
                <tr>
                    <td><label for="price">Price</label></td>
                    <td><input type="text" name="price" id="price" value="<%=pro.getPrice()%>"></td>
                </tr>
                <tr>
                    <td><label for="image">Image</label></td>
                    <td><input type="text" name="image" id="image" value="<%=pro.getImage()%>" ></td>
                </tr>
                <tr>
                    <td><label for="description">Description</label></td>
                    <td><input type="text" name="description" id="description" value="<%=pro.getDescription()%>"></td>
                </tr>
                <tr>
                    <td><label for="status">Status</label></td>
                    <td><input type="radio" name="status" id="status" value="1">Enable
                        <input type="radio" name="status" id="status" value="0">Disable</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Product" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
