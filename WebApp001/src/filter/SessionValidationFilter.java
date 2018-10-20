package filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/*"})
public class SessionValidationFilter implements Filter {
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("SessionValidationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("SESSION VALIDATION FILTER______________________________");
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        //Cookie: userLogin and userPassword 
        String userLogin = null;
        String userPassword = null;
        
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
			for (Cookie cookie : cookies) {
			   switch(cookie.getName()){
			   	case "userLogin": 		userLogin = cookie.getValue();break;
			   	case "userPassword": 	userPassword = cookie.getValue();break;
			   }
			}
		}
        
        //Path and Page from request
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String page = null;
		String pageRequest = request.getParameter("page");
		
		
        System.out.println("userLogin: "+userLogin);
        System.out.println("userPassword: "+userPassword);
        System.out.println("page: "+page);
        System.out.println("path: "+path);
        
        if(path!=null && !path.equals("/page")){
	        if(path.equals("/login.jsp") || path.equals("/registration.jsp")){
	        	System.out.println("1");
	        	
	        }else{
	        	if (userLogin != null &&  userPassword != null) {
	        		System.out.println("2");
//	        		res.sendRedirect("login");
//	        		res.sendRedirect("home.jsp");
//	        		session.setAttribute("filter", "home");
//	        		req.getRequestDispatcher("page").forward(req, res);
//	                String requestURI = req.getRequestURI();
//	                System.out.println(requestURI.indexOf("/WebApp001")+1);
//	                System.out.println(requestURI.lastIndexOf(".jsp")+1);
//	                String toReplace = requestURI.substring(requestURI.indexOf("/WebApp001")+1, requestURI.lastIndexOf("p") + 1);
//	                String newURI = requestURI.replace(toReplace, "home.jsp");
//	                System.out.println(newURI);
//                  req.getRequestDispatcher(/page).forward(req, res);
	        		

	            }else{
	            	System.out.println("3");
//	            	res.sendRedirect("login");
//	            	res.sendRedirect("index.jsp");
//	            	 res.sendRedirect("index.jsp");
//	            	 chain.doFilter(req, res);            	
	            }
	        }
        }
        	chain.doFilter(req, res);
        
        
        
            	
	}
	
	public void destroy() {
		
	}
}
