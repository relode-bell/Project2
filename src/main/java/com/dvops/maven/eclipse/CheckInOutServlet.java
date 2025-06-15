
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
    	        }

    	        con.close();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }

    	    if (attendance != null) {
    	        request.setAttribute("status", (attendance.getCheckOutTime() == null) ? "Checked In" : "Checked Out");
    	        request.setAttribute("checkinDisplay", attendance.getCheckInTime());
    	        request.setAttribute("checkoutDisplay", attendance.getCheckOutTime());
    	    } else {
    	        request.setAttribute("status", "Not Checked In");
    	    }

    	    // Show correct button
    	    request.setAttribute("showCheckIn", attendance == null || attendance.getCheckOutTime() != null);
    	    request.setAttribute("showCheckOut", attendance != null && attendance.getCheckOutTime() == null);

    	    request.setAttribute("email", email);
    	    request.getRequestDispatcher("checkinout.jsp").forward(request, response);
    	}



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            HttpSession session = request.getSession(false);
            String email = (String) session.getAttribute("email");

            if (email == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String action = request.getParameter("action");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");

                if ("checkin".equals(action)) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (email, checkin_time) VALUES (?, NOW())");
                    ps.setString(1, email);
                    ps.executeUpdate();
                    ps.close();
                } else if ("checkout".equals(action)) {
                    PreparedStatement ps = con.prepareStatement(
                        "UPDATE attendance SET checkout_time = NOW() WHERE email = ? AND checkout_time IS NULL ORDER BY id DESC LIMIT 1"
                    );
                    ps.setString(1, email);
                    ps.executeUpdate();
                    ps.close();
                }

			
			con.close();
			response.sendRedirect("CheckInOutServlet");

			}
			catch (Exception exception) {
				System.out.println(exception);
			}

	}

}
