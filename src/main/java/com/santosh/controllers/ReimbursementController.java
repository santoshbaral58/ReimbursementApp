package com.santosh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.santosh.beans.Reimbursement;
import com.santosh.services.ReimbursementService;

public class ReimbursementController {

	private Logger log = Logger.getRootLogger();
	private ReimbursementService rs = new ReimbursementService();

	public void delegateGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("get request in Reimbursement controller");
		String actualURL = request.getRequestURI()
				.substring(request.getContextPath().length() + "/reimbursement".length());

		request.getRequestDispatcher("/static/reimbursemnets.html").forward(request, response);

		if (actualURL.equals("/") || actualURL.equals("")) {

				// List for storing all the Reimbursement
				List<Reimbursement> rl = new ArrayList<>();

				// Getting currently logged in UserId
				int userId = (int) request.getSession(false).getAttribute("userId");

				// Adding it to the list
				rl = rs.getReimbursement(userId);

				log.trace(rl);

				// convert list to json
				ObjectMapper om = new ObjectMapper();
				ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(rl);

				// write json to the body of the response
				PrintWriter writer = response.getWriter();
				writer.write(json);

				log.debug("wrote reimbs to body of the response");
				return;

		}
	}

	public void delegatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.debug("post request delegated to Reimbursement controller");
		String actualURL = request.getRequestURI()
				.substring(request.getContextPath().length() + "/reimbursement".length());

		if ("".equals(actualURL)) {
			try {
				String json = request.getReader() // get the buffered reader
						.lines() // stream it
						.reduce((acc, cur) -> acc + cur) // reduce it to a single value
						.get(); // get that single value
				log.trace("json received = " + json);
				ObjectMapper om = new ObjectMapper();
				Reimbursement r = om.readValue(json, Reimbursement.class);
				log.trace("object created from json = " + r);

				rs.saveReimbursement(r);
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

}
