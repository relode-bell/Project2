<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
</head>
<body>
   <h1>Welcome</h1>
    <ul>
        <li><a href="CheckInOutServlet">Check In / Out</a></li>
        <li><a href="AttendanceHistory">Attendance Page</a></li>
        <li><a href="user-management">User Management</a></li>
    </ul>
    
    <form action="index.jsp" method="get">
        <input type="submit" value="Logout" />
    </form>
</body>
</html>