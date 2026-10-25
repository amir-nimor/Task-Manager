<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/25/2026
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update task</title>
</head>
<body>



<%
Long id = Long.parseLong(request.getParameter("id"));

%>

<%
    HttpSession session1 = request.getSession();
    Integer visiteCount = (Integer) session1.getAttribute("visitCount");
%>

<h2>your visite site = <%=visiteCount%></h2>

<form method="post" action="Task/update">

    <label>title:
    <input type="text" name="title">
    </label>

    <label>description:
    <input type="number" name="estimatedMinutes">
    </label>

    <label>project id
    <input type="number" name="projectId">
    </label>

    <label>user id
        <input type="number" name="userId">
    </label>

    <input type="hidden" name="id" value="<%= id %>">

    <button type="submit">Update</button>

</form>

</body>
</html>
