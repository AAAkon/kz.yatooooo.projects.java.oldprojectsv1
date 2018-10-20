package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/page")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//userLogin session
		String userLogin = (String)session.getAttribute("userLogin");
		
		//page, pageRequest and pageSession
		String page = request.getParameter("page");
		
		System.out.println("PageServlet______________________________");
		System.out.println("Request page:"+page);
		
			if(userLogin!=null){
				switch(page){
					case "home":	page = "home";			break;
					case "posts":	page = "posts";			break;
					default:		page = "home";			break;
				}
			}else{
				switch(page){
					case "index":			page = "index";			break;
					case "login":			page = "login";			break;
					case "registration":	page = "registration";	break;
					case "posts":			page = "posts";			break;
					default:				page = "index";			break;
				}
			}
			
			response.sendRedirect(page+".jsp");
		}
}
