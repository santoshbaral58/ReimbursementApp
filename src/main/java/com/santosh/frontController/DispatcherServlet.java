package com.santosh.frontController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.santosh.controllers.EmployeeController;
import com.santosh.controllers.LoginController;
import com.santosh.controllers.ReimbursementController;
import com.santosh.services.EmployeeService;

public class DispatcherServlet extends DefaultServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5198226055892808713L;

	private static Logger log = Logger.getRootLogger();

	private EmployeeController ec = new EmployeeController();
	private ReimbursementController rc = new ReimbursementController();
	private LoginController lc = new LoginController();
	private EmployeeService es = new EmployeeService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actualURL = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("this is doGet method from dispatcher sevlet" + actualURL);
		log.debug("actualURL: " + actualURL);

		if (actualURL.startsWith("/static/")) {
			super.doGet(request, response);
			return;
		}
		else if (actualURL.startsWith("/employee/")) {
			ec.delegateGet(request, response);
		}
		else if (actualURL.startsWith("/reimbursements/")) {
			rc.delegateGet(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("post request made with url" + request.getRequestURI());
		String actualURL = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(actualURL);

		if(actualURL.startsWith("/static")) {
			super.doPost(request, response);
			return;
		}
		else if (actualURL.startsWith("/employee/")) {
			ec.delegatePost(request, response);
		}
		else if (actualURL.startsWith("/reimbursement/")) {
			rc.delegatePost(request, response);
		}
		
	}
}
