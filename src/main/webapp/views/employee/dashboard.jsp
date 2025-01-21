<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/14/2024
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/styles.css">

</head>
<body>
<div class="container">
    <h1>Welcome to the Employee Dashboard</h1>
    <p>Hello, <%= session.getAttribute("user") %>! Here you can view your tasks and update your profile.</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listLeaves">View Leavs</a></li>
        <li><a href="${pageContext.request.contextPath}/saveEmployee">Edit Personal Info</a></li>
        <li><a class="logout" href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
</div>
</body>
</html>
