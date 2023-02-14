<%-- 
    Document   : Customer
    Created on : Feb 14, 2023, 10:25:06 AM
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
        <h1>Hello World!</h1>
        <!-- Scriplets -->
        <%
            int a = 10;
            out.print("a="+a);
        %>
        
        <%for (int i = 0; i < 100; i+=10){%>
            <hr width="<%=i%>">
        <%}%>
        
        <%
            double x = 10.1, y = 11.2;
        %>
        <%!
            public double total(double x, double y){
            return x+y;
}
        %>
        <%
            out.print("x+y=" + total(x,y));
        %>
    </body>
</html>
