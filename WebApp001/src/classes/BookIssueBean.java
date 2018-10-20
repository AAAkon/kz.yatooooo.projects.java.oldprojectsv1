package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookIssueBean extends DBUtil{
	private int issue_id;
	private int user_id;
	private String user_name;
	private int book_id;
	private String book_name;
	private Timestamp date;
	
	
	public BookIssueBean() {
		
	}
	
	public BookIssueBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
	}
	
	public BookIssueBean( int issue_id, int user_id, String user_name,
			int book_id, String book_name, Timestamp date) {
		this.issue_id = issue_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.book_id = book_id;
		this.book_name = book_name;
		this.date = date;
	}
	
	
	public int getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}
	public int getUser_id() {
		return user_id;
	} 
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public boolean isIssued(int user_id,int book_id){
		List<BookIssueBean> lists = BookIssueBean.getIssuedBooks();
		boolean existed = false;

		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getUser_id()==user_id && lists.get(i).getBook_id()==book_id){
				existed = true;
				break;
			}
		}
		
		return existed;
	}
	
	public boolean isIssued(int book_id){
		List<BookIssueBean> lists = BookIssueBean.getIssuedBooks();
		boolean existed = false;

		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getBook_id()==book_id){
				existed = true;
				break;
			}
		}
		
		return existed;
	}
	
	public void delete(int user_id,int book_id){
		try{
            
			String query = "DELETE FROM book_issue WHERE user_id="+user_id+" and book_id="+book_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public void issueBook(int user_id,int book_id,String book_name){
		
		try {
			
			List<UserBean> user = UserBean.getUser(user_id);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  
			System.out.println("Userbean: "+ user.get(0).getUsername());
			System.out.println(timestamp);
				
			String query = "INSERT INTO book_issue ( user_id, user_name, book_id,book_name,date) VALUES ( ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt (1, user_id);
		  	preparedStmt.setString (2, user.get(0).getUsername());
		  	preparedStmt.setInt (3, book_id);
		  	preparedStmt.setString (4, book_name);
		  	preparedStmt.setTimestamp (5, timestamp);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<BookIssueBean> getIssuedBooks(){
		
	 	List<BookIssueBean> list = new ArrayList<BookIssueBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from book_issue");
            
            while(rs.next())
            {
            	//int issue_id, int user_id, String user_name,int book_id, String book_name, Timestamp date
            	list.add(new BookIssueBean(rs.getInt("issue_id"),rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("book_id"),rs.getString("book_name"),rs.getTimestamp("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	
	
}
