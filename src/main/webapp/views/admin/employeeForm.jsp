<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10/14/2024
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- employeeForm.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>${employee == null ? 'Add Employee' : 'Edit Employee'}</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/employeeForm.css">
</head>
<body>
<h2>${employee == null ? 'Add Employee' : 'Edit Employee'}</h2>
<form action="<c:url value='/saveEmployee'/>" method="post">
  <input type="hidden" name="id" value="${employee.id}">

  <label>Username:</label>
  <input type="text" name="username" value="${employee.username}" required><br>

  <label>Address:</label>
  <input type="text" name="address" value="${employee.address}" required><br>

  <label>Password:</label>
  <input type="password" name="password" value="${employee.password}" required><br>

  <label>Salary:</label>
  <input type="number" step="0.01" name="salary" value="${employee.salary}" required><br>

  <label>Date of Birth:</label>
  <input type="date" name="dateOfBirth" value="${employee.dateOfBirth}" required><br>

  <label>Social Security Number:</label>
  <input type="text" name="socialSecurityNumber" value="${employee.socialSecurityNumber}" required><br>

  <label>Hire Date:</label>
  <input type="date" name="hireDate" value="${employee.hireDate}" required><br>

  <label>Performance Rating:</label>
  <input type="number" step="0.01" name="performanceRating" value="${employee.performanceRating}" required><br>

  <label>Leave Balance:</label>
  <input type="number" step="0.01" name="leaveBalance" value="${employee.leaveBalance}" required><br>

  <label>Number of Children:</label>
  <input type="number" name="children_number" value="${employee.children_number}" required><br>

  <label>Department:</label>
  <select name="department.id" required>
    <c:forEach var="dept" items="${departments}">
      <option value="${dept.id}" ${employee != null && employee.department != null && employee.department.id == dept.id ? 'selected' : ''}>
          ${dept.name}
      </option>
    </c:forEach>
  </select><br>

  <input type="submit" value="${employee == null ? 'Add' : 'Update'}">
</form>
</body>
</html>

