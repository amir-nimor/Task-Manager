<%@ page import="ir.maktabsharif.dto.task.TaskResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/25/2026
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Successfully</title>
</head>
<body>


<%
    HttpSession session1 = request.getSession();
    Integer visiteCount = (Integer) session1.getAttribute("visitCount");
    TaskResponseDto taskResponseDto = (TaskResponseDto) session1.getAttribute("taskResponseDto");
%>

<h2>your visite site = <%=visiteCount%></h2>

<h1>Update Successfully</h1>

<h2><%=taskResponseDto%></h2>



</body>
</html>
