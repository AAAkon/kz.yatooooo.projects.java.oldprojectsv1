package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class StockBean extends DBUtil{
	
	private int book_id;
	private String book_name;
	private String author_name;
	private int edition;
	private int stock;
	private boolean deleted;
	

	public StockBean(){
		
	}
	
	public StockBean(int book_id, String book_name, String author_name,
			int edition, int stock, boolean deleted) {
		this.book_id = book_id;
		this.book_name = book_name;
		this.author_name = author_name;
		this.edition = edition;
		this.stock = stock;
		this.deleted = deleted;
	}

	public StockBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getEdition(int book_id){
		int edition = 0;
		
        try{
            
            ResultSet rs = stmt.executeQuery("select * from stock");
            
            while(rs.next())
            {
            	if(rs.getInt("book_id")==book_id){
            		edition = rs.getInt("edition");
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		
		return edition;
	}
	
	public int getStock(int book_id){
		int stock = 0;
		
        try{
            
            ResultSet rs = stmt.executeQuery("select * from stock");
            
            while(rs.next())
            {
            	if(rs.getInt("book_id")==book_id){
            		stock = rs.getInt("stock");
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		
		return stock;
	}
	
	public String getAuthor_name(int book_id){
		String name = "";
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from stock");
            
            while(rs.next())
            {
            	if(rs.getInt("book_id")==book_id){
            		name = rs.getString("author_name");
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		
		return name;
	}
	
	public static List<StockBean> getBooks(){
		
	 	List<StockBean> list = new ArrayList<StockBean>();
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from stock");
            
            while(rs.next())
            {
            	list.add(new StockBean(rs.getInt("book_id"),rs.getString("book_name"),rs.getString("author_name"),rs.getInt("edition"),rs.getInt("stock"),rs.getBoolean("deleted")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public static List<StockBean> getBooks(String key){
		
	 	List<StockBean> list = new ArrayList<StockBean>();
	 	int keyint = 0;
	 	if(isNumeric(key)){
	 		 keyint = Integer.parseInt(key);
	 	}
        try{
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM stock WHERE book_name like ? or author_name like ? or edition like ? or stock like ?");
        	pstmt.setString(1, "%" + key+"%");
        	pstmt.setString(2, "%" + key+"%");
        	pstmt.setString(3, "%" + keyint+"%");
        	pstmt.setString(4, "%" + keyint+"%");
        	ResultSet rs = pstmt.executeQuery();
            //ResultSet rs = stmt.executeQuery("select * from stock where book_name like %"+key+"%");
            
            while(rs.next())
            {
            	list.add(new StockBean(rs.getInt("book_id"),rs.getString("book_name"),rs.getString("author_name"),rs.getInt("edition"),rs.getInt("stock"),rs.getBoolean("deleted")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	
	public void addBook(String book_name,String author_name,int edition,int stock,int copies){
		
		try {
		  
			String query = "INSERT INTO stock ( book_name, author_name, edition,stock,copies) VALUES ( ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("PreparedStatement start");
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, book_name);
		  	preparedStmt.setString (2, author_name);
		  	preparedStmt.setInt (3, edition);
		  	preparedStmt.setInt (4, stock);
		  	preparedStmt.setInt (5, copies);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int id){
		try {
			String query = "UPDATE stock SET deleted='"+1+"' where book_id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void returnBook(int id){
		try {
			String query = "UPDATE stock SET deleted='"+0+"' where book_id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	public int GetCopies(int book_id){
		int copies = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM stock WHERE book_id = ?");
        	pstmt.setInt(1, book_id);
        	ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
            	copies = rs.getInt("copies");
            }
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return copies;
	}
	
	public void UpdateCopies(int book_id,int count){
		try {
			String query = "UPDATE stock SET copies=copies+"+count+" where book_id='"+book_id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
