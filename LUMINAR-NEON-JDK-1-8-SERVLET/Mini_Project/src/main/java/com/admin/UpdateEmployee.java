package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/miniproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve form parameters
            int userId = Integer.parseInt(request.getParameter("userId"));
            String username = request.getParameter("username");
            String role = request.getParameter("role");
            String department = request.getParameter("department");
            String division = request.getParameter("division");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                // Create prepared statement for updating employee details
                String sql = "UPDATE employees SET username = ?, role = ?, department = ?, division = ?, first_name = ?, last_name = ? WHERE user_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, role);
                    stmt.setString(3, department);
                    stmt.setString(4, division);
                    stmt.setString(5, firstName);
                    stmt.setString(6, lastName);
                    stmt.setInt(7, userId);

                    // Execute the update
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        out.print("<html><body>");
                        out.print("<h1>Employee details updated successfully!</h1>");
                        out.print("<p><a href='EmployeeVED'>Back to Employee List</a></p>");
                        out.print("</body></html>");
                    } else {
                        out.print("<html><body>");
                        out.print("<h1>Error: Unable to update employee details.</h1>");
                        out.print("<p><a href='EmployeeVED'>Back to Employee List</a></p>");
                        out.print("</body></html>");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(out);
            out.print("<html><body>");
            out.print("<h1>Error: " + e.getMessage() + "</h1>");
            out.print("<p><a href='EmployeeVED'>Back to Employee List</a></p>");
            out.print("</body></html>");
        } finally {
            out.close();
        }
    }
}
