package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.UserBean;


@WebServlet("/AddPriveligies")
public class AddPriveligiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddPriveligiesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		int id = Integer.parseInt(request.getParameter("id"));
		UserBean userbean = new UserBean();
		
		if(type.equals("deleteFromAdmin")){
			userbean.updateAdmin(0, id);
		}else if(type.equals("addToAdmin")){
			userbean.updateAdmin(1, id);
		}else if(type.equals("deleteFromLibrarian")){
			userbean.updateLibrarian(0, id);
		}else if(type.equals("addToLibrarian")){
			userbean.updateLibrarian(1, id);
		}else if(type.equals("wrongUser")){
			userbean.updateRightUser(0, id);
		}else if(type.equals("rightUser")){
			userbean.updateRightUser(1, id);
		}else if(type.equals("add")){
			userbean.updateDeletedUser(0, id);
		}else if(type.equals("delete")){
			userbean.updateDeletedUser(1, id);
		}
		
		response.sendRedirect("users.jsp");
	}

}
