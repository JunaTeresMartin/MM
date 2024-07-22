//package com.luminar;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/LoginServlet")
//public class LoginServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        String userId = request.getParameter("userid");
//        String password = request.getParameter("password");
//        String userRole = request.getParameter("userRole");
//
//        String query = "";
//        String redirectionPage = "";
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String jdbcUrl = "jdbc:mysql://localhost:3306/miniproject";
//            String username = "root";
//            String pwd = "root";
//            Connection conn = DriverManager.getConnection(jdbcUrl, username, pwd);
//
//            if ("admin".equals(userRole)) {
//                query = "SELECT * FROM admin WHERE admin_id=? AND admin_password=?";
//                redirectionPage = "adminDashboard.html";
//            } else if ("employee".equals(userRole)) {
//                query = "SELECT * FROM employees WHERE user_id=? AND password=?";
//                redirectionPage = "employeeDashboard.html";
//            } else if ("manager".equals(userRole)) {
//                query = "SELECT * FROM managers WHERE user_id=? AND emp_password=?";
//                redirectionPage = "managerDashboard.html";
//            } else {
//            	// Redirect back to login page
//                response.sendRedirect("login.html"); 
//                return;
//            }
//
//            ps = conn.prepareStatement(query);
//            ps.setString(1, userId);
//            ps.setString(2, password);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                // If a row is found, store user info in session
//                HttpSession session = request.getSession();
//                session.setAttribute("userId", userId);
//                session.setAttribute("userRole", userRole);
//
//                // Redirect to dashboard page
//                response.sendRedirect(redirectionPage);
//            } else {
//                // Redirect back to login page if login fails
//                out.println("<html><body>");
//                out.println("<script type=\"text/javascript\">");
//                out.println("alert('Incorrect credentials');");
//                out.println("window.location.href = 'login.html';");
//                out.println("</script>");
//                out.println("</body></html>");
//            }
//            rs.close();
//            ps.close();
//            conn.close();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            // Redirect back to login page on error
//            out.println("<html><body>");
//            out.println("<script type=\"text/javascript\">");
//            out.println("alert('An error occurred. Please try again later.');");
//            out.println("window.location.href = 'login.html';");
//            out.println("</script>");
//            out.println("</body></html>");
//        }
//    }
//}
//


package com.luminar;

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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String userRole = request.getParameter("userRole");

        String query = "";
        String redirectionPage = "";
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/miniproject";
            String username = "root";
            String pwd = "root";
            Connection conn = DriverManager.getConnection(jdbcUrl, username, pwd);

            if ("admin".equals(userRole)) {
                query = "SELECT * FROM admin WHERE admin_id=? AND admin_password=?";
                redirectionPage = "adminDashboard.html";
            } else if ("employee".equals(userRole)) {
                query = "SELECT * FROM employees WHERE user_id=? AND password=?";
                redirectionPage = "employeeDashboard.html"; 
            } else if ("manager".equals(userRole)) {
                query = "SELECT * FROM managers WHERE user_id=? AND password=?";
                redirectionPage = "managerDashboard.html";
            } else {
                // Redirect back to login page
                response.sendRedirect("login.html");
                return;
            }

            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // If a row is found, store user info in session
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("userRole", userRole);

                // Fetch and store employee name for employees
                if ("employee".equals(userRole)) {
                    String empName = rs.getString("first_name"); // Assuming emp_name column exists
                    session.setAttribute("empName", empName);
                }

                // Redirect to dashboard page
                response.sendRedirect(redirectionPage);
            } else {
                // Redirect back to login page if login fails
                out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Incorrect credentials');");
                out.println("window.location.href = 'login.html';");
                out.println("</script>");
                out.println("</body></html>");
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Redirect back to login page on error
            out.println("<html><body>");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('An error occurred. Please try again later.');");
            out.println("window.location.href = 'login.html';");
            out.println("</script>");
            out.println("</body></html>");
        }
    }
}
