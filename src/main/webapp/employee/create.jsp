<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>User Management Application</title>
</head>
<body>
<center>
  <h1>User Management</h1>
  <h2>
    <a href="employee?action=users">List All Users</a>
  </h2>
</center>
<div align="center">
  <form method="post">
    <table border="1" cellpadding="5">
      <caption>
        <h2>Add New Employee</h2>
      </caption>
      <tr>
        <th>Employee Name:</th>
        <td>
          <input type="text" name="name" id="name" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Employee Email:</th>
        <td>
          <input type="text" name="email" id="email" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Address:</th>
        <td>
          <input type="text" name="address" id="address" size="15"/>
        </td>
      </tr>
      <tr>
        <th>Phone:</th>
        <td>
          <input type="text" name="phone" id="phone" size="15"/>
        </td>
      </tr>
      <tr>
        <th>Salary:</th>
        <td>
          <input type="text" name="salary" id="salary" size="15"/>
        </td>

      </tr>
      <tr>
        <th>Department:</th>
        <td>
          <select name="department" id="">
            <option value="1">Sales</option>
            <option value="2">Marketing</option>
            <option value="3">Engineering</option>
            <option value="4">Finance</option>
          </select>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="Save"/>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>