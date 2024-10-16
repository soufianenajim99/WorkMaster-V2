<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/16/2024
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Job Offer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/listJobOffers.css">
</head>
<body>
<h2>Add a New Job Offer</h2>
<form action="${pageContext.request.contextPath}/addJobOffer" method="post">
    <label for="jobTitle">Job Title:</label>
    <input type="text" name="jobTitle" id="jobTitle" required><br>
    <label for="jobDescription">Job Description:</label>
    <textarea name="jobDescription" id="jobDescription" required></textarea><br>
    <label for="validUntil">Valid Until:</label>
    <input type="date" name="validUntil" id="validUntil" required><br>
    <button type="submit">Add Job Offer</button>
</form>
</body>
</html>

