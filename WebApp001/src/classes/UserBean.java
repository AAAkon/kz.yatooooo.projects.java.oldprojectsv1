package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserBean extends DBUtil{
	private int id;
	private String name;
	private String city;
	private String address;
	private String phoneNo;
	private String email;
	private String username;
	private String password;
	private boolean rightUser;
	private boolean deletedUser;
	private boolean admin;
	private boolean librarian;
	
	public UserBean() {	
		
	}
	
	public UserBean(String DB_Path, String login, String password) {
		super(DB_Path, login, password);
		// TODO Auto-generated constructor stub
	}

	public UserBean(int id, String name, String city, String address,
			String phoneNo, String email, String username, String password2, boolean rightUser, boolean deletedUser,
			boolean admin, boolean librarian) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.username = username;
		password = password2;
		this.rightUser = rightUser;
		this.deletedUser = deletedUser;
		this.admin = admin;
		this.librarian = librarian;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRightUser() {
		return rightUser;
	}
	public void setRightUser(boolean rightUser) {
		this.rightUser = rightUser;
	}
	public boolean isDeletedUser() {
		return deletedUser;
	}
	public void setDeletedUser(boolean deletedUser) {
		this.deletedUser = deletedUser;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isLibrarian() {
		return librarian;
	}
	public void setLibrarian(boolean librarian) {
		this.librarian = librarian;
	}
	
	public static List<UserBean> getUser(int id){
		
	 	List<UserBean> list = new ArrayList<UserBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from users");
            
            while(rs.next())
            {
            	if(id==rs.getInt("id")){
            		list.add(new UserBean(rs.getInt("id"),rs.getString("name"),rs.getString("city"),rs.getString("address"),rs.getString("phoneNo"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getBoolean("rightUser"),rs.getBoolean("deletedUser"),rs.getBoolean("admin"),rs.getBoolean("librarian")));
            		break;
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public static List<UserBean> getUsers(){
		
	 	List<UserBean> list = new ArrayList<UserBean>();;
	 	
        try{
            
            ResultSet rs = stmt.executeQuery("select * from users");
            
            while(rs.next())
            {
            	list.add(new UserBean(rs.getInt("id"),rs.getString("name"),rs.getString("city"),rs.getString("address"),rs.getString("phoneNo"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getBoolean("rightUser"),rs.getBoolean("deletedUser"),rs.getBoolean("admin"),rs.getBoolean("librarian")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
	}
	
	public void updateAdmin(int value, int id){
		try {
			String query = "UPDATE users SET admin='"+value+"' where id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateLibrarian(int value, int id){
		try {
			String query = "UPDATE users SET librarian='"+value+"' where id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateRightUser(int value, int id){
		try {
			String query = "UPDATE users SET rightUser='"+value+"' where id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDeletedUser(int value, int id){
		try {
			String query = "UPDATE users SET deletedUser='"+value+"' where id='"+id+"'";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
	  		preparedStmt.executeUpdate();
	      
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
