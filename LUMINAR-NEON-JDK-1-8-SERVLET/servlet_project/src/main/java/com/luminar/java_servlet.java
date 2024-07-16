package com.luminar;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; // Added HttpServletResponse import

public class java_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void service(HttpServletRequest request, HttpServletResponse response) {
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) { // Corrected parameter type from HttpServletRequest to HttpServletResponse
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            String s = request.getParameter("t1");
            pw.println("<h1>Hello</h1>" + s); // Corrected closing h1 tag
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
