
package com.dvops.maven.eclipse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckInOutServlet
 */
@WebServlet("/CheckInOutServlet")
public class CheckInOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CheckInOutServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Access session
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");

        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");
			
			if (action.equals("checkin")) {
                String sql = "INSERT INTO attendance (email, checkin_time) VALUES (?, NOW())";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
                ps.close();
            }
			
			else if (action.equals("checkout")) {
                String sql = "UPDATE attendance SET checkout_time = NOW() " + "WHERE email = ? AND checkout_time IS NULL ORDER BY id DESC LIMIT 1";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.executeUpdate();
                ps.close();
            }
			
			con.close();
            response.sendRedirect("checkinout.jsp");

			
			}
			catch (Exception exception) {
				System.out.println(exception);
			}
		
	}

}
