<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/25/2026
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Successfully</title>
</head>
<body>
<%
    HttpSession session1 = request.getSession();
    Integer visiteCount = (Integer) session1.getAttribute("visitCount");
%>

<h2>your visite site = <%=visiteCount%></h2>

<h1>Delete Successfully</h1>

</body>
</html>
