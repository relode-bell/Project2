<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<h1>Login Page</h1> 
		
		<!-- Create a form with the action attribute to specific where to send the form-data when 
		the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE, 
		Etc.) --> 
	<h2>Login</h2>
    <form action="LoginServlet" method="post">
        Email: <input type="text" name="email" /><br />
        Password: <input type="password" name="password" /><br />
        <input type="submit" value="Login" />
    </form>
</body>
</html>