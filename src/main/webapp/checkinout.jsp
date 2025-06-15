<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true" import="java.sql.*, java.util.*, java.sql.Timestamp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String email = (String) session.getAttribute("email");
    if (email == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String status = "Not Checked In";
    boolean showCheckIn = true;
    boolean showCheckOut = false;
    String checkinDisplay = "";
    String checkoutDisplay = "";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM attendance WHERE email = ? ORDER BY id DESC LIMIT 1");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Timestamp checkin = rs.getTimestamp("checkin_time");
            Timestamp checkout = rs.getTimestamp("checkout_time");

            if (checkin != null) {
                checkinDisplay = checkin.toString();
            }
            if (checkout != null) {
                checkoutDisplay = checkout.toString();
            }

            if (checkin != null && checkout == null) {
                status = "Checked In";
                showCheckIn = false;
                showCheckOut = true;
            } else if (checkin != null && checkout != null) {
                status = "Checked Out";
                showCheckIn = true;
                showCheckOut = false;
            }
        }

        rs.close();
        ps.close();
        conn.close();
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }

    request.setAttribute("email", email);
    request.setAttribute("status", status);
    request.setAttribute("showCheckIn", Boolean.valueOf(showCheckIn));
    request.setAttribute("showCheckOut", Boolean.valueOf(showCheckOut));
    request.setAttribute("checkinDisplay", checkinDisplay);
    request.setAttribute("checkoutDisplay", checkoutDisplay);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check In / Out</title>
</head>
<body>

	<h2>Email: <c:out value="${email}" /></h2>
	<h3>Status: <c:out value="${status}" /></h3>

	<c:if test="${status eq 'Checked In'}">
	    <p>Checked in at: <c:out value="${checkinDisplay}" /></p>
	    <p>Checked out at: <c:out value="${checkoutDisplay}" /></p>
	</c:if>

	<c:if test="${status eq 'Checked Out'}">
	    <p>Checked in at: <c:out value="${checkinDisplay}" /></p>
	    <p>Checked out at: <c:out value="${checkoutDisplay}" /></p>
	</c:if>

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
