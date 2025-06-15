package com.dvops.maven.eclipse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckInOutServlet
 */
public class CheckInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CheckInOutServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        Attendance attendance = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project2", "root", "1234");

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM attendance WHERE email = ? ORDER BY id DESC LIMIT 1");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Timestamp checkIn = rs.getTimestamp("checkin_time");
                Timestamp checkOut = rs.getTimestamp("checkout_time");

                attendance = new Attendance(email, checkIn, checkOut);

                request.setAttribute("status", attendance.getStatus());
                request.setAttribute("checkinDisplay", attendance.getCheckInDisplay());
                request.setAttribute("checkoutDisplay", attendance.getCheckOutDisplay());
            } else {
                request.setAttribute("status", "Not Checked In");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Decide which button to show
        request.setAttribute("showCheckIn", attendance == null || attendance.getCheckOut() != null);
        request.setAttribute("showCheckOut", attendance != null && attendance.getCheckOut() == null);

        request.setAttribute("email", email);
        request.getRequestDispatcher("checkinout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");

            // Check latest entry
            PreparedStatement psCheck = con.prepareStatement(
                "SELECT * FROM attendance WHERE email = ? ORDER BY id DESC LIMIT 1");
            psCheck.setString(1, email);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                Timestamp checkIn = rs.getTimestamp("checkin_time");
                Timestamp checkOut = rs.getTimestamp("checkout_time");

                if (checkIn != null && checkOut == null) {
                    // User is currently checked in → update checkout_time
                    PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE attendance SET checkout_time = CURRENT_TIMESTAMP WHERE id = ?");
                    psUpdate.setInt(1, rs.getInt("id"));
                    psUpdate.executeUpdate();
                    psUpdate.close();
                } else {
                    // Already checked out → new check-in
                    PreparedStatement psInsert = con.prepareStatement(
                        "INSERT INTO attendance (email, checkin_time) VALUES (?, CURRENT_TIMESTAMP)");
                    psInsert.setString(1, email);
                    psInsert.executeUpdate();
                    psInsert.close();
                }
            } else {
                // No previous record → check in
                PreparedStatement psInsert = con.prepareStatement(
                    "INSERT INTO attendance (email, checkin_time) VALUES (?, CURRENT_TIMESTAMP)");
                psInsert.setString(1, email);
                psInsert.executeUpdate();
                psInsert.close();
            }

            rs.close();
            psCheck.close();
            con.close();

            response.sendRedirect("CheckInOutServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
