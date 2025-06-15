<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
</head>
<body>

<h2>All Users</h2>

<c:forEach var="user" items="${userList}">
    <p>
        Name: ${user.name}<br>
        Password: •••••<br>
        Email: ${user.email}<br>
        <a href="user-management?action=edit&email=${user.email}">Edit</a> |
        <a href="user-management?action=delete&email=${user.email}">Delete</a>
    </p>
    <hr>
</c:forEach>


<br><a href="userdashboard.jsp">Back to Dashboard</a>

</body>
</html>
