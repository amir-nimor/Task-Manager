<%@ page import="ir.maktabsharif.dto.task.TaskResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Successfully</title>
</head>
<body>

<%
HttpSession session1 = request.getSession(false);
if (session1 == null){
    response.sendError(404);
    return;
}

    TaskResponseDto taskResponseDto = (TaskResponseDto) session1.getAttribute("task");
%>

<h2>Add Successfully</h2>

<h2><%=taskResponseDto.toString()%></h2>

</body>
</html>
