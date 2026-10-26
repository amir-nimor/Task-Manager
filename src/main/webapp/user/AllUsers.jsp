<%@ page import="java.util.List" %>
<%@ page import="ir.maktabsharif.dto.user.UserResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show users</title>
</head>
<body>

<%
    List<UserResponseDto> userResponseDtos =(List<UserResponseDto>) request.getAttribute("users");

    for (UserResponseDto u :userResponseDtos){%>
        <h3><%=u.toString()%></h3>
    <%}%>


</body>
</html>
