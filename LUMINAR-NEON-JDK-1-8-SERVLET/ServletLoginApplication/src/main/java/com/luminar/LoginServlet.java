package com.luminar;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)

			throws ServletException, IOException { 
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String uname = req.getParameter("uname");
		pw.println("Welcome " + uname);

	}
}
