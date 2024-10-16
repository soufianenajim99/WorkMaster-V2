<%@ page import="entities.Admin" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/14/2024
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recruiter Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/styles.css">
</head>
<body>
<div class="container">
    <h1>Welcome to the Recruiter Dashboard</h1>
    <p>Hello, <%= session.getAttribute("user") %>! Here you can manage job postings and candidates.</p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/listJobOffers">Manage Job Postings</a></li>
        <li><a href="viewApplicants">View Applicants</a></li>
        <li><a class="logout" href="../logout">Logout</a></li>
    </ul>
</div>
</body>
</html>

