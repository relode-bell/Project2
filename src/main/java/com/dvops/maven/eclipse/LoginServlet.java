package com.dvops.maven.eclipse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");
			
			String sql = "SELECT * FROM project2 WHERE email = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			// Store Select
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
			    HttpSession session = request.getSession(); 
			    session.setAttribute("email", email); 

			    response.sendRedirect("userdashboard.jsp");
			} else {
			    PrintWriter out = response.getWriter();
			    out.println("<h3 style='color:red;'>Invalid email or password. Please try again.</h3>");
			}


			
			rs.close();
            ps.close();
            con.close();
           

			}
			//Step 8: catch and print out any exception
			catch (Exception exception) {
				System.out.println(exception);
			}
		
	}

}
