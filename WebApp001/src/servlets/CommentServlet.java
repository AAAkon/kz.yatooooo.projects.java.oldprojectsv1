package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.CommentBean;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentServlet() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentBean commentbean = new CommentBean();
		String action = request.getParameter("action");
		int user_id = (int)request.getSession().getAttribute("userID");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		if(action.equals("add_comment")){
			String text = request.getParameter("text");
			
			commentbean.AddComment(text, post_id, user_id);
		}else if(action.equals("delete_comment")){
			int comment_id = Integer.parseInt(request.getParameter("comment_id"));
			commentbean.DeleteComment(comment_id);
		}
		
		response.sendRedirect("post?action=post_details&post_id="+post_id);
	}

}
