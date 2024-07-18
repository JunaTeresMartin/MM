package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeEdit")
public class EmployeeEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));

            // Here you can fetch the details of the employee with the given userId from the database
            // For demonstration, let's just display the userId

            out.print("<html><body>");
            out.print("<h1>Edit Employee</h1>");
            out.print("<p>User ID: " + userId + "</p>");

            // Include form to edit employee details here
            out.print("<form method='post' action='UpdateEmployee'>");
            out.print("    <input type='hidden' name='userId' value='" + userId + "' />");
            out.print("    <label for='username'>Username:</label>");
            out.print("    <input type='text' id='username' name='username' /><br />");
            out.print("    <label for='role'>Role:</label>");
            out.print("    <input type='text' id='role' name='role' /><br />");
            out.print("    <label for='department'>Department:</label>");
            out.print("    <input type='text' id='department' name='department' /><br />");
            out.print("    <label for='division'>Division:</label>");
            out.print("    <input type='text' id='division' name='division' /><br />");
            out.print("    <label for='firstName'>First Name:</label>");
            out.print("    <input type='text' id='firstName' name='firstName' /><br />");
            out.print("    <label for='lastName'>Last Name:</label>");
            out.print("    <input type='text' id='lastName' name='lastName' /><br />");
            out.print("    <button type='submit'>Update</button>");
            out.print("</form>");

            out.print("</body></html>");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
