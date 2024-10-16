<%@ page import="entities.Admin" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/14/2024
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    Admin admin = (Admin) session.getAttribute("user");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/styles.css">
</head>
<body>
<div class="container">
    <h1>Welcome to the Admin Dashboard</h1>
    <p>Hello, <%= admin.getId()  %>! Here you can manage the system.</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/manageEmployees">Manage Users</a></li>
        <li><a href="viewReports">View Reports</a></li>
        <li><a class="logout" href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
</div>
</body>
</html>

