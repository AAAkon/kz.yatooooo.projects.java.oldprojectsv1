package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostLikeBean extends DBUtil{
	private int post_id;
	private int user_id;
	
	public PostLikeBean() {
		
	}

	public PostLikeBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
	}
	
	public PostLikeBean(int post_id, int user_id) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
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
	
	public boolean isLikeClicked(int post_id,int user_id){
		boolean result = false;
		
		try{
            
            ResultSet rs = stmt.executeQuery("select * from post_likes");
            
            while(rs.next())
            {
                if(rs.getInt("post_id")==post_id && rs.getInt("user_id")==user_id){
                	result = true;
                	break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
	public void AddPostLike(int post_id, int user_id){
		try {
			  
			String query = "INSERT INTO post_likes ( post_id,user_id) VALUES (?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt (1, post_id);
		  	preparedStmt.setInt (2, user_id);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DeletePostLike(int post_id, int user_id){
		try {
			  
			String query = "DELETE FROM post_likes WHERE post_id="+post_id+" and user_id="+user_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
