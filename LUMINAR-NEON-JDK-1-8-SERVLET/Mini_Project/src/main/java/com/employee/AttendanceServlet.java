package com.employee;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This servlet will handle punch in and punch out actions
    }

    protected Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/attendance_db";
        String username = "root";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
