<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/16/2024
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Job Offers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/listJobOffers.css">
</head>
<body>
<a href="<c:url value="/addJobOffer"/>">Add New Job Offer</a>
<h2>Job Offers</h2>
<table>
    <thead>
    <tr>
        <th>Job Title</th>
        <th>Description</th>
        <th>Status</th>
        <th>Posted Date</th>
        <th>Valid Until</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="jobOffer" items="${jobOffers}">
        <tr>
            <td>${jobOffer.jobTitle}</td>
            <td>${jobOffer.jobDescription}</td>
            <td>${jobOffer.status}</td>
            <td>${jobOffer.postedDate}</td>
            <td>${jobOffer.validUntil}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

