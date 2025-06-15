<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
</head>
<body>

<h2>Edit User</h2>

<form action="user-management?action=update" method="post">
    <input type="hidden" name="originalEmail" value="${user.email}"/>
    Name: <input type="text" name="name" value="${user.name}" required><br>
    Password: <input type="password" name="password" value="${user.password}" required><br>
    Email: <input type="email" name="email" value="${user.email}" required><br>
    <input type="submit" value="Update">
</form>


<br><a href="user-management">Cancel</a>

</body>
</html>
