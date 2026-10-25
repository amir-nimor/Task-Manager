<%@ page import="java.util.List" %>
<%@ page import="ir.maktabsharif.dto.task.TaskResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/25/2026
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Show All task</title>
</head>
<body>

<%
    HttpSession session1 = request.getSession();
    Integer visiteCount = (Integer) session1.getAttribute("visitCount");
%>

<h2>your visite site = <%=visiteCount%></h2>

<%
    List<TaskResponseDto> taskResponseDtos = (List<TaskResponseDto>) request.getAttribute("tasks");

    for (TaskResponseDto t : taskResponseDtos) {%>
<div>

    <span><%=t.toString()%></span>

    <form action="Task/update"
          method="get"
          style="display: inline;">

        <input type="hidden" name="id" value="<%=t.getId()%>">
        <button type="submit">Edit</button>
    </form>

    <form action="Task/delete"
          method="post"
          style="display: inline;">

        <input type="hidden" name="id" value="<%=t.getId()%>">
        <button type="submit">Delete</button>
    </form>
</div>

<%}%>


</body>
</html>
