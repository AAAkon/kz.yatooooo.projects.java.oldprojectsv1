package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();  
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if(login!=null && password!=null){
			
			ServletContext ctx = request.getServletContext();
			DBUtil dbManager = (DBUtil) ctx.getAttribute("DBManager");
			
			if(dbManager.checkAuth(login, password)){
				String user_id = dbManager.getUserID(login)+"";
				
				//userID cookie created
				Cookie userID = new Cookie("userID",user_id);
				response.addCookie(userID);
				userID.setMaxAge(60*30); //60*30
				
				
				//userLogin cookie created
				Cookie userLogin = new Cookie("userLogin",login);
				response.addCookie(userLogin);
				userLogin.setMaxAge(60*30); //60*30
				
				//userPassword cookie created
				Cookie userPassword = new Cookie("userPassword",password);
				response.addCookie(userPassword);
				userPassword.setMaxAge(60*30); //60*30
				
				
				//userLogin and userPassword session create
				session.setAttribute("userID", userID);
				session.setAttribute("userLogin", login);
				session.setAttribute("userPassword", password);
				
				//page session created
				response.sendRedirect("home.jsp");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
	}

}
