<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/25/2026
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show My Tasks</title>
</head>
<body>

<%
HttpSession session1 = request.getSession(false);
if (session1==null){
  response.sendError(404);
  return;
}

//Integer id = (Integer) session1.getAttribute("userId");
%>

<form action="${pageContext.request.contextPath}/Task" method="get">
<%--    <input type="hidden" name="id" value="<%= id %>">--%>
    <input type="number" name="id">

    <button>Submit</button>
</form>



</body>
</html>
