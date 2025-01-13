<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/12/2025
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Job Offer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/listJobOffers.css">
</head>
<body>
<h2>Edit a New Job Offer</h2>
<form action="${pageContext.request.contextPath}/editJobOffer" method="post">
    <input type="hidden" name="id" value="${jobOffer.jobId}" />
    <label for="jobTitle">Job Title:</label>
    <input type="text" name="jobTitle" id="jobTitle" value="${jobOffer.jobTitle}" required><br>
    <label for="jobDescription">Job Description:</label>
    <textarea name="jobDescription" id="jobDescription" required>${jobOffer.jobDescription}</textarea><br>
    <label for="validUntil">Valid Until:</label>
    <input type="date" name="validUntil" id="validUntil" value="${jobOffer.validUntil}" required><br>
    <button type="submit">Update Job Offer</button>
</form>

</body>
</html>
