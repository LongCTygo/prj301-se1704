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
        <div>Menu<jsp:include page="menu.jsp"></jsp:include></div>
        <div>Content<jsp:include page="ViewProduct.jsp"></jsp:include></div>
    </body>
</html>
