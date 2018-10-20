package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostBean extends DBUtil{
	private int id;
	private String title;
	private String content;
	private int like;
	private int dislike;
	private int user_id;
	private Timestamp date;
	private String username;
	
	public PostBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
		// TODO Auto-generated constructor stub
	}
	public PostBean(){}
	public PostBean(int id, String title, String content, int like,	int dislike, int user_id, Timestamp date ,String username) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.like = like;
		this.dislike = dislike;
		this.user_id = user_id;
		this.date = date;
		this.username = username;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	public void UpdateLikeCount(int post_id, String action){
		
		try {
			ResultSet rs = stmt.executeQuery("select like_count from posts where id="+post_id);
			int like_count = 0;
			
			if(rs.next()){
				if(action.equals("add")){
					like_count = rs.getInt("like_count") + 1;
				}else if(action.equals("delete")){
					like_count = rs.getInt("like_count") - 1;
				}
			}
				
			String query = "UPDATE posts SET  like_count = " + like_count + " WHERE id="+post_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateDislikeCount(int post_id,String action){
		
		try {
			ResultSet rs = stmt.executeQuery("select dislike_count from posts where id="+post_id);
			int dislike_count = 0;
			
			if(rs.next()){
				if(action.equals("add")){
					dislike_count = rs.getInt("dislike_count") + 1;
				}else if(action.equals("delete")){
					dislike_count = rs.getInt("dislike_count") - 1;
				}
			}
				
			String query = "UPDATE posts SET  dislike_count = " + dislike_count + " WHERE id="+post_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPost(String title,String content,int like,int dislike,int user_id,String username){
		try {
			  
			String query = "INSERT INTO posts ( title, content, date, like_count, dislike_count ,user_id,username) VALUES ( ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("PostBEAN________________________________________________________________");
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, title);
		  	preparedStmt.setString (2, content);
		  	preparedStmt.setTimestamp (3, new java.sql.Timestamp(System.currentTimeMillis()));
		  	preparedStmt.setInt (4, like);
	  		preparedStmt.setInt (5, dislike);
	  		preparedStmt.setInt (6, user_id);
	  		preparedStmt.setString (7, username);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<PostBean> getPost(){
		
	 	List<PostBean> list = new ArrayList<PostBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from posts order by id desc");
            
            while(rs.next())
            {
                list.add(new PostBean(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getInt("like_count"),rs.getInt("dislike_count"),rs.getInt("user_id"),rs.getTimestamp("date"),rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public static List<PostBean> getPostById(int post_id){
		
	 	List<PostBean> list = new ArrayList<PostBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from posts where id="+post_id + " limit 1");
            
            while(rs.next())
            {
                list.add(new PostBean(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getInt("like_count"),rs.getInt("dislike_count"),rs.getInt("user_id"),rs.getTimestamp("date"),rs.getString("username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public void DeletePost(int post_id){
		try {
			  
			String query = "DELETE FROM posts WHERE id="+post_id;
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
