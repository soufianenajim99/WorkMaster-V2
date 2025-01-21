<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Leave Request</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/listJobOffers.css">
</head>
<body>
<h2>Add Leave Request</h2>

<form action="<c:url value='/submitLeaveRequest'/>" method="post">

    <!-- Request ID (hidden, generated server-side) -->
    <input type="hidden" name="requestId" value="${leaveRequest.requestId}">

    <!-- Start Date -->
    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" required><br>

    <!-- End Date -->
    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" required><br>

    <!-- Reason -->
    <label for="reason">Reason:</label>
    <textarea id="reason" name="reason" required></textarea><br>

    <!-- Status (set to 'Pending' for now, may be modified later) -->
    <input type="hidden" name="status" value="PENDING">

    <!-- Employee (Currently logged-in user; Assume this info is already available in the session) -->
    <input type="hidden" name="employeeId" value="${employee.id}">  <!-- Assuming the employee object is available in the session -->

    <input type="submit" value="Submit Leave Request">
</form>
</body>
</html>
