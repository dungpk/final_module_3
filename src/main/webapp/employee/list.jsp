<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<div style="text-align: center;">
    <h1>User Management</h1>
    <h2>
        <a href="/employee?action=create">Add New User</a>
        <form action="/employee?" method="get">
            <label for="search">Search:</label>
            <input type="hidden" name="action" value="search">
            <input placeholder="Tìm kiếm theo tên" type="text" id="search" name="search">
            <button type="submit">Search</button>
        </form>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Employee</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>address</th>
            <th>phone</th>
            <th>salary</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.address}"/></td>
                <td><c:out value="${employee.phone}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.department}"/></td>
                <td>
                    <a href="/users?action=edit&id=${employee.id}">Edit</a>
                    <a href="/users?action=delete&id=${employee.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>