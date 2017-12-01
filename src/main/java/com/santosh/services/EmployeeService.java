package com.santosh.services;

import org.apache.log4j.Logger;

import com.santosh.beans.Employee;
import com.santosh.dao.EmployeeDao;
import com.santosh.dao.EmployeeDaoJDBC;

public class EmployeeService {

	private Logger log = Logger.getRootLogger();
	private EmployeeDao ed = new EmployeeDaoJDBC();

	public Employee login(Employee emp) {
		Employee checkEmp = ed.findUser(emp.getUsername(), emp.getPassword());
																			
		if (emp.getUsername().equals(checkEmp.getUsername()) && emp.getPassword().equals(checkEmp.getPassword())) {
			log.trace("Login Successful");
			return checkEmp;
		} else {
			log.trace("Invalid Credentials");
			return null;
		}

	}
}
