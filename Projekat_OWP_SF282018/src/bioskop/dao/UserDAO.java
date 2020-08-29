package bioskop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Movie;
import model.User;
import util.DateTimeUtil;

public class UserDAO {
	
public static List<User> getAllUsers() throws Exception {
		
		List<User> users = new ArrayList<>();
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "select * from Users where Active = 1";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				
				int timeStamp = rset.getInt(index++);
				LocalDate registrationDate = DateTimeUtil.UnixTimeStampToLocalDate(timeStamp);
				User.Role userRole = User.Role.valueOf(rset.getString(index++));
				boolean active = rset.getInt(index++) == 1;
				boolean loggedIn = rset.getInt(index++) == 1;
				
				User user = new User(username, password, registrationDate, userRole, active, loggedIn);
				
				users.add(user);
				
			}
		}finally {
			if(pstmt != null) try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(rset != null) try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return users;
	}


	public static User getUserByUsername (String Username) throws Exception {
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "select * from Users where Username = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Username);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int index = 1;
				String username = rset.getString(index++);
				String password = rset.getString(index++);
				

				int timeStamp = rset.getInt(index++);
				LocalDate registrationDate = DateTimeUtil.UnixTimeStampToLocalDate(timeStamp);
				
				User.Role userRole = User.Role.valueOf(rset.getString(index++));
				boolean active = rset.getInt(index++) == 1;
				boolean loggedIn = rset.getInt(index++) == 1;
				
				return new User(username, "", registrationDate, userRole, active, loggedIn);
			}
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(rset != null) try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return null;
	}
	
	public static String getPasswordByUsername (String Username) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select Password from Users where Username = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, Username);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return rset.getString(1);
				
			}
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return null;
	}
	
	public static boolean login (User user) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "update Users set LoggedIn = 1 where Username = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			
			return pstmt.executeUpdate() == 1;
			
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
	}
	
	public static boolean register (User user) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "insert into Users values (?, ?, ?, ?, 1, 0) ";
			pstmt = connection.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getUsername());
			pstmt.setString(index++, user.getPassword());
			pstmt.setInt(index++, DateTimeUtil.LocalDateToUnixTimeStamp(user.getRegistrationDate()));
			pstmt.setString(index++, user.getRole().toString());
			
			return pstmt.executeUpdate() == 1;
			
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
	}	
}
