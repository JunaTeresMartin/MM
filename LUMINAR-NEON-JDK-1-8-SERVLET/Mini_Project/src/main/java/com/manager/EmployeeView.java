package com.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmployeeView")
public class EmployeeView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/miniproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        try {
//            displayEmployees(out,request,response);
//        } catch (Exception e) {
//            e.printStackTrace(out);
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            displayEmployees(out,request,response);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
        
   

    private void displayEmployees(PrintWriter out, HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException {
        // Load JDBC driver and establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Create statement to retrieve all employees
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = stmt.executeQuery();

            // Display table headers
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <title>Employees List</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h2>Manage Employee</h2>");
            out.println("    <h2>Employees List</h2>");

            out.println("    <table id=\"employeesTable\">");
            out.println("        <thead>");
            out.println("            <tr>");
            out.println("                <th>Userid</th>");
            out.println("                <th>Username</th>");
            out.println("                <th>Role</th>");
            out.println("                <th>Department</th>");
            out.println("                <th>Division</th>");
            out.println("                <th>First Name</th>");
            out.println("                <th>Last Name</th>");
            out.println("            </tr>");
            out.println("        </thead>");
            out.println("        <tbody>");

            // Display each employee row
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                String department = rs.getString("department");
                String division = rs.getString("division");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                out.println("            <tr>");
                out.println("                <td>" + userId + "</td>");
                out.println("                <td>" + username + "</td>");
                out.println("                <td>" + role + "</td>");
                out.println("                <td>" + department + "</td>");
                out.println("                <td>" + division + "</td>");
                out.println("                <td>" + firstName + "</td>");
                out.println("                <td>" + lastName + "</td>");
            }

            out.println("        </tbody>");
            out.println("    </table>");

            out.println("    <script>");
            out.println("        function deleteEmployee(userId) {");
            out.println("            var xhr = new XMLHttpRequest();");
            out.println("            xhr.open('POST', 'delete', true);");
            out.println("            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
            out.println("            xhr.onreadystatechange = function() {");
            out.println("                if (xhr.readyState === 4) {");
            out.println("                    if (xhr.status === 200) {");
            out.println("                        alert(xhr.responseText);");
            out.println("                        location.reload();");
            out.println("                    } else {");
            out.println("                        alert('Failed to delete employee.');");
            out.println("                    }");
            out.println("                }");
            out.println("            };");
            out.println("            xhr.send('action=delete&user_id=' + userId);");
            out.println("        }");
            out.println("    </script>");

            out.println("</body>");
            out.println("</html>");
        }
    }

        
    }
    
    
    
