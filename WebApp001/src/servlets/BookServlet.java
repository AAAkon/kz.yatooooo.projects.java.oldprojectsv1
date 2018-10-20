package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.StockBean;
import classes.BookIssueBean;
import classes.BookReturnBean;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockBean stockbean = new StockBean();
		String action = "";
		action = request.getParameter("action");
		
		if(action.equals("add")){
			String book_name = request.getParameter("book_name");
			String author_name = request.getParameter("author_name");
			int edition = Integer.parseInt(request.getParameter("edition"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int copies = Integer.parseInt(request.getParameter("copies"));
			
			stockbean.addBook(book_name, author_name, edition, stock,copies);
		}
		
		
		response.sendRedirect("stock.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "";
		StockBean stockbean = new StockBean();
		BookIssueBean bookissue = new BookIssueBean();
		BookReturnBean bookreturn = new BookReturnBean();
		String action = "";
		action = request.getParameter("action");
			
		if(action.equals("delete")){
			int id = Integer.parseInt(request.getParameter("book_id"));
			stockbean.deleteBook(id);
			page = "stock.jsp";
		}else if(action.equals("return")){
			int id = Integer.parseInt(request.getParameter("book_id"));
			stockbean.returnBook(id);
			page = "stock.jsp";
		}else if(action.equals("issued")){
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String book_name = request.getParameter("book_name");
			
			if(bookissue.isIssued((int)(request.getSession().getAttribute("userID")), book_id) && bookreturn.isReturned((int)(request.getSession().getAttribute("userID")), book_id)){
				
				stockbean.UpdateCopies(book_id, -1);
				bookreturn.delete(user_id, book_id);
				bookissue.delete(user_id, book_id);
				bookissue.issueBook(user_id,book_id,book_name);
			}else if(!bookissue.isIssued((int)(request.getSession().getAttribute("userID")), book_id) && !bookreturn.isReturned((int)(request.getSession().getAttribute("userID")), book_id)){
				stockbean.UpdateCopies(book_id, -1);
				bookissue.issueBook(user_id,book_id,book_name);
			}
			page = "stock.jsp";
		}else if(action.equals("returned")){
			
			int book_id = Integer.parseInt(request.getParameter("book_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String book_name = request.getParameter("book_name");
			
			stockbean.UpdateCopies(book_id, 1);
			bookreturn.returnBook(user_id,book_id,book_name);
			page = "users.jsp";
		}else if(action.equals("search")){
			String key = request.getParameter("key");
			request.setAttribute("key", key);
			request.getRequestDispatcher("stock.jsp").forward(request, response);
		}
		
		if(!action.equals("search")){
			response.sendRedirect(page);
		}
	}
	
	

}
