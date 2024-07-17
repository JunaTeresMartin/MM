<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@page import = "com.mvc.beans.EmployeeBean"%>
	<%@page import = "com.mvc.dao.EmployeeDAO"%>
    
    <%
    String empName=request.getParameter("empName");
    float empSalary=Float.parseFloat(request.getParameter("empSalary"));
    
    EmployeeBean employee = new EmployeeBean();
    employee.setEmpName(empName);
    employee.setEmpSalary(empSalary);
    
    
    boolean flag = EmployeeDAO.insertEmployee(employee);
    if(flag){
    	response.sendRedirect("index.html");
    }else{
    	response.sendRedirect("error.html");
    }
    
    
%>