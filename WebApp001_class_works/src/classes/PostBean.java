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
	
	public PostBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
		// TODO Auto-generated constructor stub
	}
	public PostBean(){}
	public PostBean(int id, String title, String content, int like,	int dislike, int user_id, Timestamp date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.like = like;
		this.dislike = dislike;
		this.user_id = user_id;
		this.date = date;
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
	
	public void addPost(String title,String content,int like,int dislike,int user_id){
		try {
			  
			String query = "INSERT INTO posts ( post_title, post_content, post_date, post_like, post_dislike,user_id) VALUES ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("PostBEAN________________________________________________________________");
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, title);
		  	preparedStmt.setString (2, content);
		  	preparedStmt.setTimestamp (3, new java.sql.Timestamp(System.currentTimeMillis()));
		  	preparedStmt.setInt (4, like);
	  		preparedStmt.setInt (5, dislike);
	  		preparedStmt.setInt (6, user_id);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<PostBean> getPost(){
		
	 	List<PostBean> list = new ArrayList<PostBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from posts");
            
            while(rs.next())
            {
                list.add(new PostBean(rs.getInt("post_id"),rs.getString("post_title"),rs.getString("post_content"),rs.getInt("post_like"),rs.getInt("post_dislike"),rs.getInt("user_id"),rs.getTimestamp("post_date")));
                System.out.println(rs.getString("post_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
}
