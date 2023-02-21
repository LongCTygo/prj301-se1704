<%-- 
    Document   : index
    Created on : Feb 21, 2023, 11:00:03 AM
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
        <%
            String searchValue = (String) request.getAttribute("searchValue");
            if (searchValue == null){
                searchValue = "";
            }
        %>
        <div>Menu<jsp:include page="menu.jsp"></jsp:include></div>
            <form action="ClientController">
                <input type="hidden" name="go" value="search">
                <input type="text" placeholder="Search.." name="pname" value="<%=searchValue%>">
                <button type="submit">Search!</button>
            </form>
            <div>Content<jsp:include page="ViewProduct.jsp"></jsp:include></div>
    </body>
</html>
