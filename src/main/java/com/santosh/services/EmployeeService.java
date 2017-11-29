package com.santosh.services;

import java.util.List;

import com.santosh.beans.Employee;
import com.santosh.dao.EmployeeDao;
import com.santosh.dao.EmployeeDaoJDBC;
import com.santosh.exceptions.InvalidCredentialException;

public class EmployeeService {

	private EmployeeDao ed = new EmployeeDaoJDBC();

	public List<Employee> findAllEmployee(){
		
		return ed.findAll();
	}
	
	public void login() throws InvalidCredentialException {
		throw new InvalidCredentialException(403);
	}
}
