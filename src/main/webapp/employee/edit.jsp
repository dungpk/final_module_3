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
    <a href="employee?action=employees">List All Users</a>
  </h2>
</center>
<div align="center">
  <form method="post" >
    <table border="1" cellpadding="5">
      <caption>
        <h2>
          Edit Employee
        </h2>
      </caption>
      <c:if test="${employee != null}">
        <input type="hidden" name="id" value="<c:out value='${employee.id}' />"/>
      </c:if>
      <tr>
        <th>Employee Name:</th>
        <td>
          <input type="text" name="name" size="45"
                 value="<c:out value='${employee.name}' />"
          />
        </td>
      </tr>
      <tr>
        <th>User Email:</th>
        <td>
          <input type="text" name="email" size="45"
                 value="<c:out value='${employee.email}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Address:</th>
        <td>
          <input type="text" name="address" size="15"
                 value="<c:out value='${employee.address}' />"
          />
        </td>
      </tr>



      <tr>
        <th>Phone Number:</th>
        <td>
          <input type="text" name="phone" size="15"
                 value="<c:out value='${employee.phone}' />"
          />
        </td>
      </tr>

      <tr>
        <th>Salary:</th>
        <td>
          <input type="text" name="salary" size="15"
                 value="<c:out value='${employee.salary}' />"
          />
        </td>
      </tr>

      <tr>
        <th>Salary:</th>
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