package classes;
import java.sql.*;

import com.mysql.jdbc.Connection;

public class DBUtil {
	
	public static String DB_Path = "";
	public static String login = "";
	public static String password = "";
	public static Connection conn;
	public static Statement stmt;
	
	public DBUtil(String DB_Path,String login,String password){
		try{
		  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		  Class.forName("com.mysql.jdbc.Driver");
		  conn = (Connection) DriverManager.getConnection(DB_Path, login, password); 
		  stmt = conn.createStatement();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      
	   }
	}
	
	public DBUtil(){
		
	}
	
	//Insert into USERS: create user
	public void createUser(String name,String city,String address,String phoneNo,String email,String login,String password){
		
		try {
		  
			String query = "INSERT INTO users ( username, password, name,city,address, phoneNo, email) VALUES ( ?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			System.out.println("PreparedStatement start");
			
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString (1, login);
		  	preparedStmt.setString (2, password);
		  	preparedStmt.setString (3, name);
		  	preparedStmt.setString (4, city);
		  	preparedStmt.setString (5, address);
		  	preparedStmt.setString (6, phoneNo);
	  		preparedStmt.setString (7, email);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	//check login for existing in DataBase
	//if login exist return FALSE
	//if login not exist return TRUE
	public boolean loginNotExist(String login){
		boolean check = true;
		
		try {
			
			ResultSet rs = stmt.executeQuery("select * from users");
			
			while(rs.next()){
				if(login.equals(rs.getString("login"))){
					check = false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	
	//check LOGIN and PASSWORD are CORRECT for Authorization
	public boolean checkAuth(String login,String password){
		boolean check = false;
		
		try {
			
			ResultSet rs = stmt.executeQuery("select * from users");
			
			while(rs.next()){
				if(login.equals(rs.getString("username")) && password.equals(rs.getString("password")) && rs.getBoolean("rightUser")==true && rs.getBoolean("deletedUser")==false){
					check = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	
	//get ID of USER, USERS.id
	public int getUserID(String login){
		int ID = 0;
		
		try {
			
			ResultSet rs = stmt.executeQuery("select * from users where username='"+login+"' limit 1");
			if(rs.next()){
				ID = rs.getInt("id");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ID;
	}
	
	public String getName(int id){
		String name = "";
		
		try {
			
			ResultSet rs = stmt.executeQuery("select name from users where id="+id);
			if(rs.next()){
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	//return connection
	public Connection getConnection(){
		return this.conn;
	}
	
	
	//close connection with DataBase
	public void closeConnection(){
		try {
			System.out.println("Close DB________________________________");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}