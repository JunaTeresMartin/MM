package com.luminar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PreparedStatement ps = null;
	ResultSet rs = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost:3306/test";
			String username = "root";
			String password = "root";
			Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

			ps = conn.prepareStatement("select * from student");
			rs = ps.executeQuery();

			response.setContentType("text/html");	
			PrintWriter pw = response.getWriter();
			
			
			pw.println("<html><body>");
			pw.println("<h3>Student Details</h3>");
			pw.println("<table border='1'><tr>" + "<td><b>Roll No</b></td>" + "<td><b>Name</b></td>"
					+ "<td><b>Mark</b></td>" + "</tr>");

			while (rs.next()) {
				pw.println("<tr>" + "<td>" + rs.getInt(1) + "</td>" + "<td>" + rs.getString(2) + "</td>" + "<td>"
						+ rs.getInt(3) + "</td>" + "</tr>");
			}

			pw.println("</table></body></html>");
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}