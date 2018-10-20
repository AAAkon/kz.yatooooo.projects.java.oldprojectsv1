package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import classes.UserBean;

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
			UserBean userbean = new UserBean();
			
			if(dbManager.checkAuth(login, password)){
				String userIDstring = dbManager.getUserID(login)+"";
				int userIDint = Integer.parseInt(userIDstring);
				//userID cookie created
				Cookie userID = new Cookie("userID2",userIDstring);
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
				
				List<UserBean> lists = UserBean.getUsers();
				for(int i=0;i<lists.size();i++){
					if(lists.get(i).getId()==userIDint){
						if(lists.get(i).isAdmin()==true || lists.get(i).isLibrarian()==true){
							session.setAttribute("who", "admin");
							break;
						}else{
							session.setAttribute("who", "user");
						}
					}else{
						session.setAttribute("who", "user");
					}
				}
				
				session.setAttribute("userID", userIDint);
				session.setAttribute("userLogin", login);
				session.setAttribute("userPassword", password);
				
				response.sendRedirect("home.jsp");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else{
			response.sendRedirect("login.jsp");
		}
	}

}
