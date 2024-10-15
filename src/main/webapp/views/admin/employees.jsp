<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/14/2024
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Employees</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/employees.css">
</head>
<body>
<h1>Manage Employees</h1>
<a href="<c:url value="/saveEmployee"/>">Add New Employee</a>
<table>
    <thead>
    <tr>
        <th>Username</th>
        <th>Address</th>
        <th>Hire Date</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.username}</td>
            <td>${employee.address}</td>
            <td>${employee.hireDate}</td>
            <td>${employee.salary}</td>
            <td>${employee.department.name}</td>
            <td>
<%--                <a href="editEmployee?id=${employee.id}">Edit</a> |--%>
<%--                <a href="deleteEmployee?id=${employee.id}">Delete</a>--%>
                <a href="<c:url value='/saveEmployee?id=${employee.id}'/>">Edit</a>
                <a href="<c:url value='/deleteEmployee?id=${employee.id}'/>" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

