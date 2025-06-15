<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Attendance History</title>
</head>
<body>

<h3>Attendance History</h3>

<table>
    <tr>
        <th>Email</th>
        <th>Status</th>
        <th>Date</th>
    </tr>

    <c:forEach var="record" items="${history}">
        <tr>
            <td><c:out value="${record.email}" /></td>
            <td><c:out value="${record.status}" /></td>
            <td><c:out value="${record.date}" /></td>
        </tr>
    </c:forEach>
</table>

<br>
<a href="userdashboard.jsp">Back</a>

</body>
</html>
