package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		
		if (session!= null) {
		    session.removeAttribute("userLogin");
		    session.removeAttribute("userPassword");
		    session.removeAttribute("userID");
		    session.removeAttribute("who");
		    if (cookies != null) 
			{
			    for(int i=0; i<cookies.length; i++) 
			    {
			        Cookie cookie = cookies[i];
			        cookie.setMaxAge(0);
			        response.addCookie(cookie);
			    }
			}
		}
		
		response.sendRedirect("index.jsp");
	}

}
