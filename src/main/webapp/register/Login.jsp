<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<%
    Cookie[] cookies = request.getCookies();

    String tokenEmail = null;

    for (Cookie c:cookies){
        if (c.getName().equals("token")){
            tokenEmail = c.getValue();
        }
    }

    if (tokenEmail.isBlank()){
        response.sendRedirect("./CreateUser.jsp");
        return;
    }
%>


<form id="login-form">

    <label>email:
    <input id="email" type="text" name="email">
    </label>

    <label>name:
    <input id="name" type="text" name="name">
    </label>

    <button id="btn">SUBMIT</button>
</form>

<ul id="list">

</ul>


<script>

    const btn = document.getElementById("btn");

    let list = document.getElementById("list");

    function login(){
        let object = {
            email : document.getElementById("email").value,
            name : document.getElementById("name").value
        }
        event.preventDefault();

        fetch("${pageContext.request.contextPath}/Register",{
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(object)
        }).then(response => {
            return response.json();
        }).then(data =>{
            let li = document.createElement("li");
            li.innerText = data.id + " - "+data.name+" - "+data.email;
            list.appendChild(li);
        })
    }
    btn.addEventListener("click",login);

</script>

</body>
</html>
