<%@ page import="ir.maktabsharif.dto.task.TaskResponseDto" %><%--
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

<%
    TaskResponseDto taskResponseDto =(TaskResponseDto) request.getAttribute("taskResponseDto");
%>

<h2><%=taskResponseDto%></h2>

<form id="updateTaskForm">
    <input id="ida" type="hidden" name="id" value="<%=id%>">

    <label>Title:</label>
    <input id="title" type="text" name="title" required>

    <label>Estimated Minutes:</label>
    <input id="estimatedMinutes" type="number" name="estimatedMinutes" required>

    <label>User ID:
    <input id="userID" type="text" name="userID" required>
    </label>

    <label>Project ID:
    <input id="projectID"  type="text" name="projectID" required>
    </label>


    <button id="btn" type="button">Update</button>
</form>

<script>

    const btn = document.getElementById("btn");

    function send(){
        let object = {
            id : document.getElementById("ida").value,
            title:document.getElementById("title").value,
            estimatedMinutes:document.getElementById("estimatedMinutes").value,
            userId:document.getElementById("userID").value,
            projectId:document.getElementById("projectID").value
        };

        fetch("${pageContext.request.contextPath}/Task/update",{
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body:JSON.stringify(object)
        }).then(response => {
            return response.json();
        }).then(data => {
            window.location.href="${pageContext.request.contextPath}/sucsesfully/UpdateSucsesfully.jsp";
        })
    }
    btn.addEventListener("click",send)



</script>

</body>
</html>
