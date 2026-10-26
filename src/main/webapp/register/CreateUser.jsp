<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<form id="form1">

    <label>name:
        <input id="name" type="text" name="name" placeholder="Enter your name">
    </label>

    <label>email:
        <input id="email" type="text" name="email" placeholder="Enter your email">
    </label>

    <button id="btn" >SUBMIT</button>

</form>

<ul id="list">

</ul>

<script>
    const btn = document.getElementById("btn");

    function Add() {
        let object = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
        }
        let list = document.getElementById("list");

        event.preventDefault();
        fetch("${pageContext.request.contextPath}/Register", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(object)
        }).then(response => {
            return response.json();
        }).then(data => {
            let li = document.createElement("li");
            li.innerText = data.id+" - "+ data.name+" - "+data.email;
            list.appendChild(li);
        })
    }

    btn.addEventListener("click",Add);

</script>


</body>
</html>
