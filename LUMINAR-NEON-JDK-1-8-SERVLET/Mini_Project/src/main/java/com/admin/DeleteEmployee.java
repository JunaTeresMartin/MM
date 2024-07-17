package com.admin;

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

@WebServlet("/delete")
public class DeleteEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/miniproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            displayEmployees(out);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String userIdStr = request.getParameter("user_id");
            int userId = Integer.parseInt(userIdStr);
            try {
                if (deleteEmployee(userId)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Employee with User ID " + userId + " deleted successfully.");
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Failed to delete Employee with User ID " + userId);
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace(response.getWriter());
            }
        }
    }

    private void displayEmployees(PrintWriter out) throws ClassNotFoundException, SQLException {
        // Load JDBC driver and establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Create statement to retrieve all employees
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee_manager_table");
            ResultSet rs = stmt.executeQuery();

            // Display table headers
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <title>Employees List</title>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: Arial, sans-serif;");
            out.println("            padding: 20px;");
            out.println("        }");
            out.println("        table {");
            out.println("            width: 100%;");
            out.println("            border-collapse: collapse;");
            out.println("            margin-top: 20px;");
            out.println("        }");
            out.println("        table, th, td {");
            out.println("            border: 1px solid #ddd;");
            out.println("            padding: 8px;");
            out.println("            text-align: left;");
            out.println("        }");
            out.println("        th {");
            out.println("            background-color: #f2f2f2;");
            out.println("        }");
            out.println("        form {");
            out.println("            max-width: 600px;");
            out.println("            margin: auto;");
            out.println("            border: 1px solid #ddd;");
            out.println("            padding: 20px;");
            out.println("        }");
            out.println("        form input, form select {");
            out.println("            width: 100%;");
            out.println("            padding: 10px;");
            out.println("            margin: 5px 0;");
            out.println("            box-sizing: border-box;");
            out.println("        }");
            out.println("        form button {");
            out.println("            background-color: #4CAF50;");
            out.println("            color: white;");
            out.println("            padding: 12px 20px;");
            out.println("            border: none;");
            out.println("            cursor: pointer;");
            out.println("            width: 100%;");
            out.println("        }");
            out.println("        form button:hover {");
            out.println("            background-color: #45a049;");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <h2>Delete Employee</h2>");
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
            out.println("                <th>Created At</th>");
            out.println("                <th>Updated At</th>");
            out.println("                <th>Actions</th>");
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
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");

                out.println("            <tr>");
                out.println("                <td>" + userId + "</td>");
                out.println("                <td>" + username + "</td>");
                out.println("                <td>" + role + "</td>");
                out.println("                <td>" + department + "</td>");
                out.println("                <td>" + division + "</td>");
                out.println("                <td>" + firstName + "</td>");
                out.println("                <td>" + lastName + "</td>");
                out.println("                <td>" + createdAt + "</td>");
                out.println("                <td>" + updatedAt + "</td>");
                out.println("                <td><button onclick='deleteEmployee(" + userId + ")'>Delete</button></td>");
                out.println("            </tr>");
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

    private boolean deleteEmployee(int userId) throws ClassNotFoundException, SQLException {
        // Load JDBC driver and establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            // Create prepared statement for deletion
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee_manager_table WHERE user_id = ?");
            stmt.setInt(1, userId);
            // Execute the deletion
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}