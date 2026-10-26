<%@ page import="java.util.List" %>
<%@ page import="ir.maktabsharif.dto.project.ProjectResponseDto" %><%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show All Project</title>
</head>
<body>

<%
    List<ProjectResponseDto> projectResponseDtos = (List<ProjectResponseDto>) request.getAttribute("projects");
    for (ProjectResponseDto p :projectResponseDtos){%>
        <h3><%=p.toString()%></h3>
    <%}%>


</body>
</html>
