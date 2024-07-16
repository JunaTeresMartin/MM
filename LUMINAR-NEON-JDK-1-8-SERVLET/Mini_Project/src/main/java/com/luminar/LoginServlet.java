package com.luminar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String user_id = request.getParameter("userid");
        String password = request.getParameter("password");
        String userRole = request.getParameter("userRole"); 

        String query = "";
        String dashboardPage = "";

        if ("admin".equals(userRole)) {
            query = "select * from admin_table where admin_id=? and admin_password=?";
         // Path to admin dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/admin_dashboard.html");
            rd.forward(request, response); 
        } else if ("employee".equals(userRole)) {
            query = "select * from employee_table where emp_id=? and emp_password=?";
         // Path to employee dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/employee_dashboard.html");
            rd.forward(request, response);
            
        } else {
            // To handle invalid role or other errors
            response.sendRedirect("login.html"); // Redirect back to login page
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String pwd = "root";
            Connection conn = DriverManager.getConnection(jdbcUrl, username, pwd);

            // Use PreparedStatement to avoid SQL Injection
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user_id);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // If a row is found, store user info in session
                HttpSession session = request.getSession();
                session.setAttribute("user_id", user_id);
                session.setAttribute("userRole", userRole);
                
                // Redirect to dashboard page
                response.sendRedirect(dashboardPage);
            } else {
                // Redirect back to login page if login fails
                response.sendRedirect("login.html");
            }

            // Close resources
            rs.close();
            ps.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Redirect back to login page on error
            response.sendRedirect("login.html");
        }
    }
}
