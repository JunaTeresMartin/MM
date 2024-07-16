package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/insert")
public class InsertEmployeeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String Id = request.getParameter("emp_user_id");
			int EmpId = Integer.parseInt(Id);
			String EmpUserName = request.getParameter("emp_username");
			String EmpPassword = request.getParameter("emp_password");
			String EmpRole = request.getParameter("emp_role");
			String EmpDept = request.getParameter("emp_dept");
			String EmpDivision = request.getParameter("emp_division");
			String EmpFirstName = request.getParameter("emp_first_name");
			String EmpLastName = request.getParameter("emp_last_name");
			
//			LocalDate date = LocalDate.now();
			LocalDateTime current = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formatedDateTime = current.format(format);
			
			
			
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","root");
			
			PreparedStatement stmt= conn.prepareStatement("insert into employee_table(emp_user_id,emp_username,emp_password,emp_role,emp_department,emp_division,emp_first_name,emp_last_name,emp_created_at,emp_updated_at) values (?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, EmpId);
			stmt.setString(2, EmpUserName);
			stmt.setString(3, EmpPassword);
			stmt.setString(4, EmpRole);
			stmt.setString(5, EmpDept);
			stmt.setString(6, EmpDivision);
			stmt.setString(7, EmpFirstName);
			stmt.setString(8, EmpLastName);
			stmt.setString(9,formatedDateTime);
			stmt.setString(10,formatedDateTime);
			
			stmt.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}

