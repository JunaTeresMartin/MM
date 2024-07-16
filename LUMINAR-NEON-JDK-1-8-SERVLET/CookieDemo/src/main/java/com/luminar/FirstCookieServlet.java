package com.luminar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")

public class FirstCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("userName");
			out.print("Welcome " + username);

			Cookie ck = new Cookie("uname", username);// creating cookie object
			response.addCookie(ck);// adding cookie in the response
			// creating submit button
			out.print("<form action='second' method='post'>");
			out.print("<input type='submit' value='go'>");
			out.print("</form>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
