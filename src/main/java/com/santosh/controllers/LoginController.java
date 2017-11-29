package com.santosh.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santosh.beans.Employee;
import com.santosh.services.EmployeeService;

public class LoginController {

	private Logger log = Logger.getRootLogger();
	private EmployeeService es = new EmployeeService();

	public void delegateGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Get request in Login controller");
		request.getRequestDispatcher("/static/login.html").forward(request, response);

	}

	public void delegatePost(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Post request delegated to Login controller");
		String actualURL = request.getRequestURI().substring(request.getContextPath().length() + "/".length());

		if ("".equals(actualURL)) {
			login(request, response);

		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {

			String json = request.getReader() // Get the buffered reader for reading request body
					.lines() // Stream it
					.reduce((acc, curr) -> acc + curr) // Convert lines to one String
					.get(); // Get that single string value
			ObjectMapper om = new ObjectMapper();
			Employee emp = om.readValue(json, Employee.class);
			log.trace("username received: " + emp.getUsername());
			log.trace("password received: " + emp.getPassword());
			Employee actualEmp = es.login(emp);
			if (actualEmp == null) {
				response.setStatus(401);
			} else {
				response.setStatus(200);
				request.getSession().setAttribute("userId", actualEmp.getUserId());
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
