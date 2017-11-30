package com.santosh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.santosh.beans.Employee;
import com.santosh.dao.EmployeeDao;
import com.santosh.dao.EmployeeDaoJDBC;
import com.santosh.services.EmployeeService;

public class EmployeeController {

	private Logger log = Logger.getRootLogger();
	private EmployeeDao ed = new EmployeeDaoJDBC();
	private EmployeeService es = new EmployeeService();

	public void delegateGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		log.debug("get request delegated to employee controller");
		String actualURL = request.getRequestURI().substring(request.getContextPath().length() + "/employee".length());

		if ("/".equals(actualURL) || "".equals(actualURL)) {

				// get all employees from EmployeeDao
				List<Employee> allEmployee = ed.findAll();

				// converting List to JSON
				ObjectMapper om = new ObjectMapper();
				ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(allEmployee);

				// write JSON to the body of the response
				PrintWriter writer = response.getWriter();
				writer.write(json);

		}

	}
	
	
	public void delegatePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String actualURL = request.getRequestURI().substring(request.getContextPath().length());
		if ("/users/login".equals(actualURL)) {
			String json;
            try {
                log.debug("request to login received");
                
                json = request.getReader().lines().reduce((acc, cur) -> acc + cur).get();
                log.trace("json received: " + json);
                
                ObjectMapper om = new ObjectMapper();
                Employee emp = om.readValue(json, Employee.class);
                log.trace("username received: " + emp.getUsername());
                log.trace("password received: " + emp.getPassword());
                Employee actualEmp = es.login(emp);
                
                if (actualEmp == null){
                	response.setStatus(401);
                }
                else {
                	request.getSession().setAttribute("employee",  actualEmp);
                }
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
	}

}
