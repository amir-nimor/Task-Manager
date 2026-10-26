<%@ page import="ir.maktabsharif.dto.user.UserResponseDto" %>
<%@ page import="ir.maktabsharif.dto.project.ProjectResponseDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Tasks</title>
</head>
<body>

<%
    List<UserResponseDto> users =
            (List<UserResponseDto>) request.getAttribute("users");

    List<ProjectResponseDto> projects =
            (List<ProjectResponseDto>) request.getAttribute("projects");
%>

<form id="creatr form">

    <label>Title:</label>
    <input id="title" type="text" name="title" required>

    <br><br>

    <label>Estimated Minutes:</label>
    <input id="estimatedMinutes" type="number" name="estimatedMinutes"
            required>

    <br><br>

    <label>Assignee:</label>
    <select id="assigneeId" name="assigneeId" required>
        <option value="">Select User</option>

        <% for (UserResponseDto user : users) { %>
        <option value="<%= user.getId() %>">
            <%= user.getId() %>
        </option>
        <% } %>
    </select>

    <br><br>

    <label>Project:</label>
    <select id="projectId" name="projectId" required>
        <option value="">Select Project</option>

        <% for (ProjectResponseDto project : projects) { %>
        <option value="<%= project.getId() %>">
            <%= project.getId() %>
        </option>
        <% } %>
    </select>

    <br><br>

    <button id="btn" type="submit">Create Task</button>
</form>



<script>
    const btn = document.getElementById("btn");



    function add(){
        const object = {
            title:document.getElementById("title").value,
            estimatedMinutes:document.getElementById("estimatedMinutes").value,
            userId:document.getElementById("assigneeId").value,
            projectId:document.getElementById("projectId").value
        }

        fetch("${pageContext.request.contextPath}/Task/Add",{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body:JSON.stringify(object)
        }).then(response => {
            return response.json();
        }).then(data => {
            window.location.href="${pageContext.request.contextPath}/sucsesfully/AddSucsesfully.jsp";
        })

    }

    btn.addEventListener("click",add)

</script>

</body>
</html>
