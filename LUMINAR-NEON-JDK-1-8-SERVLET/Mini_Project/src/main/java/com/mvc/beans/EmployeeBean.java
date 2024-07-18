package com.mvc.beans;

public class EmployeeBean {
/*
 * @author : Juna Teres Martin
 * @date : 17-07-2024
 * @purpose : 
 */
	
	private int empId;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empname;
	}
	public void setEmpName(String empname) {
		this.empname = empname;
	}
	public float getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(float empSalary) {
		this.empSalary = empSalary;
	}
	private String empname;
	private float empSalary;
}
