package com.santosh.dao;

import java.util.List;

import com.santosh.beans.Employee;

public interface EmployeeDao {
	
	List<Employee> findAll();
	Employee findUser(String username, String password);
}
