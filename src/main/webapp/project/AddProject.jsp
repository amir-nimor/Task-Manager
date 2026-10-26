<%--
  Created by IntelliJ IDEA.
  User: Soly
  Date: 10/26/2026
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Project</title>
</head>
<body>

<form method="post" action="projects/Add">

    <label>name:
    <input id="name" type="text" name="name">
    </label>

    <label>description:
    <input id="description" type="text" name="description">
    </label>

    <button id="btn" type="submit">Submit</button>

</form>

<ul id="list">


</ul>

<script>

    const btn  = document.getElementById("btn");
    let list = document.getElementById("list");

    function add(){
        let object ={
            name : document.getElementById("name").value,
            description : document.getElementById("description").value
        }
        event.preventDefault()

        fetch("${pageContext.request.contextPath}/projects/Add",{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body:JSON.stringify(object)
        }).then(response => {
            return response.json()
        }).then(data => {
            let li = document.createElement("li");
            li.innerText = data.id+" - "+data.name+" - "+data.description;
            list.appendChild(li);
        })
    }



</script>

</body>
</html>
