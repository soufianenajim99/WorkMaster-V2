<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/16/2024
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Job Offers</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/condidat.css">
</head>
<body>
<h1>Job Offers</h1>
<c:if test="${not empty jobOffers}">
    <ul>
        <c:forEach var="offer" items="${jobOffers}">
            <li>
                <h3>${offer.jobTitle}</h3>
                <p>${offer.jobDescription}</p>
                <form action="${pageContext.request.contextPath}/applyJob" method="post">
                    <input type="hidden" name="jobId" value="${offer.jobId}">
                    <label for="name">Your Name:</label>
                    <input type="text" name="name" id="name" required><br>
                    <label for="email">Your Email:</label>
                    <input type="email" name="email" id="email" required><br>
                    <label for="resume">Your Resume:</label>
                    <textarea name="resume" id="resume" required></textarea><br>
                    <button type="submit">Apply</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty jobOffers}">
    <p>No job offers available at the moment.</p>
</c:if>
</body>
</html>

