package frontController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.santosh.exceptions.InvalidCredentialException;
import com.santosh.exceptions.UrlNotRecognizedException;
import com.santosh.services.EmployeeService;

public class DispatcherServlet extends DefaultServlet {

	private EmployeeController ec = new EmployeeController();
	private ReimbursementController rc = new ReimbursementController();
	private EmployeeService es = new EmployeeService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		
		String actualURL = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(actualURL);
		
		if(actualURL.startsWith("/static")) {
			super.doGet(request, response);
			return;
		} else if ("/home".equals(actualURL)) {
			//forward the client url will not change
			request.getRequestDispatcher("/static/index.html").forward(request,response);
		} else if (actualURL.startsWith("/employee")) {
			ec.delegateGet(request, response);
		} else if(actualURL.startsWith("/reimbursement")) {
			rc.delegateGet(request, response);
		} else {
			throw new UrlNotRecognizedException();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		try {
			System.out.println("post request made with url" + request.getRequestURI());
			String actualURL = request.getRequestURI().substring(request.getContextPath().length());
			
				if(actualURL.startsWith("/reimbursement")) {
					rc.delegatePost(request, response);
				} else if ("/login".equals(actualURL)) {
					System.out.println("login");
					es.login();
				}
		} catch (InvalidCredentialException e) {
			response.setStatus(e.getStatusCode());
		}
	}

}
