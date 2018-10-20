package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentBean extends DBUtil{
	private int id;
	private String text;
	private Timestamp date;
	private int like_count;
	private int dislike_count;
	private int post_id;
	private int user_id;
	
	public CommentBean(){
		
	}
	
	
	public CommentBean(int id, String text, Timestamp date,
			int like_count, int dislike_count, int post_id, int user_id) {
		this.id = id;
		this.text = text;
		this.date = date;
		this.like_count = like_count;
		this.dislike_count = dislike_count;
		this.post_id = post_id;
		this.user_id = user_id;
	}


	public CommentBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getDislike_count() {
		return dislike_count;
	}
	public void setDislike_count(int dislike_count) {
		this.dislike_count = dislike_count;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public void AddComment(String text, int post_id, int user_id){
		try {
			System.out.println("Comment");
			String query = "INSERT INTO comments ( text, date, like_count, dislike_count,post_id ,user_id) VALUES ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, text);
		  	preparedStmt.setTimestamp (2, new java.sql.Timestamp(System.currentTimeMillis()));
		  	preparedStmt.setInt (3, 0);
	  		preparedStmt.setInt (4, 0);
	  		preparedStmt.setInt (5, post_id);
	  		preparedStmt.setInt (6, user_id);
	  		preparedStmt.executeUpdate();
	  		System.out.println("Added");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeleteComment(int comment_id){
		try {
			  
			String query = "DELETE FROM comments WHERE id="+comment_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<CommentBean> getCommentByPostId(int post_id){
		
	 	List<CommentBean> list = new ArrayList<CommentBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from comments where post_id="+post_id);
            
            while(rs.next())
            {
                list.add(new CommentBean(rs.getInt("id"),rs.getString("text"),rs.getTimestamp("date"),rs.getInt("like_count"),rs.getInt("dislike_count"),rs.getInt("post_id"),rs.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
}
