package com.dvops.maven.eclipse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		
		//Step 2: retrieve the four parameters from the request from the web form
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2", "root", "1234");
		
		//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
		PreparedStatement ps = con.prepareStatement("insert into project2 values(?,?,?)");
		
		//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, email);
		//Step 6: perform the query on the database using the prepared statement
		int status = ps.executeUpdate();
		//Step 7: check if the query had been successfully executed, return “You are successfully registered” via the response,
		
		if (status > 0){
			PrintWriter writer = response.getWriter();
		    writer.println("<html><body>");
		    writer.println("<h2>You have successfully registered an account!</h2>");
		    writer.println("<p>You will be redirected to the login page in 3 seconds...</p>");
		    writer.println("<script>");
		    writer.println("setTimeout(function(){ window.location.href = 'login.jsp'; }, 3000);");
		    writer.println("</script>");
		    writer.println("</body></html>");
		    writer.close();
			}
		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		
		doGet(request, response);
	}

}
