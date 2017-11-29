package controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.santosh.beans.Employee;
import com.santosh.services.EmployeeService;

public class EmployeeController {
	
	private Logger log = Logger.getRootLogger();
	private EmployeeService es = new EmployeeService();
	
	public void delegateGet(HttpServletRequest request, HttpServletResponse response) {
		
		log.debug("get request delegated to employee controller");
		String actualURL = request.getRequestURI().substring(request.getContextPath().length() +"/employee".length());
	
		if("/".equals(actualURL) || "".equals(actualURL)) {
			
			try { 
				//get all of the users from the service
				List<Employee> allEmployee = es.findAllEmployee();
				
				//convert arraylist to json
				ObjectMapper om = new ObjectMapper();
				ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(allEmployee);
				
				//write json to the body of the response
				PrintWriter writer = response.getWriter();
				writer.write(json);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
	}

}

