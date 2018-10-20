package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.PostBean;
import classes.PostDislikeBean;
import classes.PostLikeBean;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PostServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getParameter("action");
		PostBean p = new PostBean();
		PostLikeBean post_like = new PostLikeBean();
		PostDislikeBean post_dislike = new PostDislikeBean();
		
		int user_id = (int)request.getSession().getAttribute("userID");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		if(action.equals("post_details")){
			
		}else if(action.equals("add_like")){
			if(post_like.isLikeClicked(post_id, user_id)){
				
			}else{
				p.UpdateLikeCount(post_id,"add");
				post_like.AddPostLike(post_id, user_id);
				if(post_dislike.isDislikeClicked(post_id, user_id)){
					p.UpdateDislikeCount(post_id,"delete");
					post_dislike.DeletePostDislike(post_id, user_id);
				}
			}
			
		}else if(action.equals("delete_like")){
			if(post_like.isLikeClicked(post_id, user_id)){
				p.UpdateLikeCount(post_id,"delete");
				post_like.DeletePostLike(post_id,user_id);
			}
			
		}else if(action.equals("add_dislike")){

			if(post_dislike.isDislikeClicked(post_id, user_id)){
				
			}else{
				p.UpdateDislikeCount(post_id, "add");
				post_dislike.AddPostDislike(post_id,user_id);
				if(post_like.isLikeClicked(post_id, user_id)){
					p.UpdateLikeCount(post_id, "delete");
					post_like.DeletePostLike(post_id, user_id);
				}
			}
		}else if(action.equals("delete_dislike")){
			if(post_dislike.isDislikeClicked(post_id, user_id)){
				p.UpdateDislikeCount(post_id, "delete");
				post_dislike.DeletePostDislike(post_id,user_id);
			}
		}else if(action.equals("delete_post")){
			
			p.DeletePost(post_id);
			response.sendRedirect("posts.jsp");
		}
		
		if(!action.equals("delete_post")){
			request.setAttribute("post_id", post_id);
			request.getRequestDispatcher("post_details.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getParameter("action");
		PostBean p = new PostBean();
		
		if(action.equals("add_post")){
			
			int userID =(int) request.getSession().getAttribute("userID");
			String username = (String)request.getSession().getAttribute("userLogin");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			p.addPost(title, content, 0, 0, userID,username);
		}else{}
		
		
		
		
		
			
		
		
		response.sendRedirect("posts.jsp");
	}

}
