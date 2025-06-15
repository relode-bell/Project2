<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<h1>Register New User</h1>
	<form action="register" method="post">
		Name : <input type="text" name="userName"><br>
		Password : <input type="password" name="password"><br>
		Email : <input type="text" name="email"><br>
		<input type="submit" value = "Register"/>
	</form>
</body>
</html>