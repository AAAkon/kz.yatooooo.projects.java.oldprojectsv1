package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BookReturnBean extends DBUtil{
	private int return_id;
	private int user_id;
	private String user_name;
	private int book_id;
	private String book_name;
	private Timestamp date;
	
	
	public BookReturnBean() {
		
	}
	
	public BookReturnBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
	}
	
	public BookReturnBean( int return_id, int user_id, String user_name,
			int book_id, String book_name, Timestamp date) {
		this.return_id = return_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.book_id = book_id;
		this.book_name = book_name;
		this.date = date;
	}
	
	
	public int getReturn_id() {
		return return_id;
	}
	public void setReturn_id(int return_id) {
		this.return_id = return_id;
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
	
	public boolean isReturned(int user_id,int book_id){
		List<BookReturnBean> lists = BookReturnBean.getReturnedBooks();
		boolean existed = false;

		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getUser_id()==user_id && lists.get(i).getBook_id()==book_id){
				existed = true;
				break;
			}
		}
		
		return existed;
	}
	
	
	public static List<BookReturnBean> getReturnedBooks(){
		
	 	List<BookReturnBean> list = new ArrayList<BookReturnBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from book_return");
            
            while(rs.next())
            {
            	//int issue_id, int user_id, String user_name,int book_id, String book_name, Timestamp date
            	list.add(new BookReturnBean(rs.getInt("return_id"),rs.getInt("user_id"),rs.getString("user_name"),rs.getInt("book_id"),rs.getString("book_name"),rs.getTimestamp("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public void returnBook(int user_id,int book_id,String book_name){
		
		try {
		  
			List<UserBean> user = UserBean.getUser(user_id);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  
			String query = "INSERT INTO book_return ( user_id, user_name, book_id,book_name,date) VALUES ( ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("PreparedStatement start");
			
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
	
	public void delete(int user_id,int book_id){
		try{
            
			String query = "DELETE FROM book_return WHERE user_id="+user_id+" and book_id="+book_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
}
