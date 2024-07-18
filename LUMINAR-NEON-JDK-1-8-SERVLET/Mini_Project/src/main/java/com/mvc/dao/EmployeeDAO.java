package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.beans.EmployeeBean;

public class EmployeeDAO {
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	static boolean flag = false;

	// --------------START------get connection---------------
	public static Connection getDbConnection() throws SQLException {
		try {
			DBDAO.connect();
			con = DBDAO.getDbCon();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// ------------------END-------------database connection---------

	// --------------START------insert details-------------------------

	public static boolean insertEmployee(EmployeeBean employeeBean) throws SQLException {
		try {
			con = getDbConnection();
			pst = con.prepareStatement("insert into employees(emp_name,salary)values(?,?)");
			pst.setString(1, employeeBean.getEmpName());
			pst.setFloat(2, employeeBean.getEmpSalary());
			pst.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	// --------------END------insert details-------------------------

	// --------------START------get employee details-------------------------
	public static ArrayList<EmployeeBean> listAllEmployees() throws SQLException {
		ArrayList<EmployeeBean> employeeBean = new ArrayList<EmployeeBean>();
		con = getDbConnection();
		try {
			pst = con.prepareStatement("SELECT * FROM employees");
			rs = pst.executeQuery();
			while (rs.next()) {
				EmployeeBean employee = new EmployeeBean();
				employee.setEmpId(rs.getInt(1));
				employee.setEmpName(rs.getString(2));
				employee.setEmpSalary(rs.getFloat(3));
				employeeBean.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeBean;
	}

	// --------------END------list employee details-------------------------

	// ---------------------------------fetchEmployeeById--------------------------------------
	public static ArrayList<EmployeeBean> fetchEmployeeById(int empId) throws SQLException {
		ArrayList<EmployeeBean> employeeBean = new ArrayList<EmployeeBean>();
		con = getDbConnection();
		try {
			pst = con.prepareStatement("SELECT * FROM employees where emp_id=" + empId + " ");
			rs = pst.executeQuery();
			while (rs.next()) {
				EmployeeBean employee = new EmployeeBean();
				employee.setEmpId(rs.getInt(1));
				employee.setEmpName(rs.getString(2));
				employee.setEmpSalary(rs.getFloat(3));
				employeeBean.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeBean;
	}
	// --------------END----------------------------------------------------------------

	// ---------------------------------------------------------
	public static boolean updateEmployee(EmployeeBean employeeBean) throws Exception {
		con = getDbConnection();
		boolean flag = false;
		try {
			pst = con.prepareStatement("update employees set emp_name=?, salary=?  where emp_id =?");
			pst.setString(1, employeeBean.getEmpName());
			pst.setFloat(2, employeeBean.getEmpSalary());
			pst.setInt(3, employeeBean.getEmpId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			con.close();
		}
		return flag;
	}
	// ---------------------------------------end-------------------------------

	// --------------------------start Delete  Employee-------------------------------
	public static boolean deleteEmployee(EmployeeBean employeeBean) throws Exception {
		con = getDbConnection();
		boolean flag = false;
		try {
			pst = con.prepareStatement("delete from employees where emp_id =?");
			System.out.println("delete from employees where emp_id =" + employeeBean.getEmpId() + "");
			pst.setInt(1, employeeBean.getEmpId());
			pst.executeUpdate();
			flag = true;
		} catch (SQLException sql) {
			sql.printStackTrace();
		} finally {
			con.close();
		}
		return flag;
	}
	// ---------------------------------------end-------------------------------

}
