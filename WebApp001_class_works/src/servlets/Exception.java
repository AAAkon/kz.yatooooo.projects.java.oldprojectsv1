package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exception
 */
@WebServlet("/exception")
public class Exception extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exception() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		
		// Set response content type
	      response.setContentType("text/html");
	 
	      PrintWriter out = response.getWriter();
	      out.write("<html><head><title>Exception/Error Details</title></head><body>");
	      
    	  out.write("<h3>Exception Details</h3>");
    	  out.write("<ul><li>Servlet Name:"+servletName+"</li>");
    	  out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
    	  out.write("<li>Requested URI:"+requestUri+"</li>");
    	  out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
    	  out.write("</ul>");
      
	      out.write("<br><br>");
	      out.write("<a href=\"page?page=index\">Home Page</a>");
	      out.write("</body></html>");

	
		
	}

}
