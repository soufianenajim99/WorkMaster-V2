<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="role">Login as:</label>
            <select id="role" name="role" required>
                <option value="admin">Admin</option>
                <option value="recruiter">Recruiter</option>
                <option value="employee">Employee</option>
            </select>
        </div>
        <button type="submit">Login</button>
    </form>
    <div class="apply-job">
        <h2>Or</h2>
        <p>If you want to apply for a job offer, <a href="">click here</a>.</p>
    </div>
</div>
</body>
</html>