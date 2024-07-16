package com.luminar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dis = null;
	public void doPost (HttpServletRequest request, HttpServletResponse response ) 
	throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("uname");
		String pass = request.getParameter("upass");
		if(name.equals("Peter") && pass.equals("1234")) {
			dis = request.getRequestDispatcher("success");
			dis.forward(request, response);
		}else {
			out.print("user name or password is incorrect!");
			dis = request.getRequestDispatcher("index.html");
			dis.include(request, response);
		}
	}
}
