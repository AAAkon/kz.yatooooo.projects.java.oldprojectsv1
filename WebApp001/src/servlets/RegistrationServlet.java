package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.DBUtil;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegistrationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String address = request.getParameter("address");
		String phoneNo = request.getParameter("phoneNo");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if(name!="" && city!="" && address!="" && address!="" && phoneNo!="" && email!="" && login!="" && password!="" && password2!=""){
			if(password.equals(password2)){
				
				ServletContext ctx = request.getServletContext();
				DBUtil d = (DBUtil) ctx.getAttribute("DBManager");
				
				if(d.loginNotExist(login)){
					d.createUser(name,city,address,phoneNo,email,login,password);
					response.sendRedirect("login.jsp");
				}else{
					response.sendRedirect("registration.jsp");
				}
			}else{
				response.sendRedirect("registration.jsp");
			}
		}else{
			response.sendRedirect("registration.jsp");
		}
		
	}//End doPost()

}
