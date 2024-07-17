package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			pst = con.prepareStatement("insert into employee(emp_name,salary)values(?,?)");
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
}
