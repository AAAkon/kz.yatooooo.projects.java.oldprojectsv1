package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.PostBean;

@WebServlet("/addPost")
public class addPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public addPostServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PostBean p = new PostBean();
//		String userID = "-1";

		int userID = p.getUserID((String) request.getSession().getAttribute("userLogin"));
//		Cookie[] cookies = request.getCookies();
//		  if(cookies != null) {
//		      for (int i = 0; i < cookies.length; i++) {
//		          Cookie cookie = cookies[i];
//		          if(cookie.getName().equals("userID")){
//		        	  userID = cookie.getValue();
//		        	  break;
//		          }
//		       }
//		   }
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		if(request.getSession().getAttribute("userLogin")!=null){
			p.addPost(title, content, 0, 0, userID);
		}else{
			
		}
		
		response.sendRedirect("posts.jsp");
		
	}

}
