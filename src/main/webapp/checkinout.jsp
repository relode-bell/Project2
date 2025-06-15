<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check In / Out</title>
</head>
<body>

<h2>User: <c:out value="${email}" /></h2>
<h3>Status: <c:out value="${status}" /></h3>

<p>Checked in at: <c:out value="${checkinDisplay}" /></p>
<p>Checked out at: <c:out value="${checkoutDisplay}" /></p>

<c:if test="${showCheckIn}">
    <form action="CheckInOutServlet" method="post">
        <input type="hidden" name="action" value="checkin">
        <input type="submit" value="Check In">
    </form>
</c:if>

<c:if test="${showCheckOut}">
    <form action="CheckInOutServlet" method="post">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Check Out">
    </form>
</c:if>

<br><a href="userdashboard.jsp">Back</a>

</body>
</html>
