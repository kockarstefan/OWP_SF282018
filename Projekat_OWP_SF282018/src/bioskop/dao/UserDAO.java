package bioskop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import model.User;
import model.User.Role;

public class UserDAO {

	public static User get(String korisnickoIme, String lozinka) throws Exception {
		ConnectionManager.open();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT ID,datum_Reg,uloga FROM korisnik WHERE kor_Ime = ? AND lozinka = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnickoIme);
			pstmt.setString(index++, lozinka);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int ID = Integer.parseInt(rset.getString(1));
				String datumReg = rset.getString(2);
				Role role = Role.valueOf(rset.getString(3));

				return new User(ID,korisnickoIme,lozinka,datumReg,role);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}

		return null;
	}
	
	
	public Map<String, User> getAllUsers() throws Exception {
		
		Map<String, User> users = new LinkedHashMap<>();
		
		ConnectionManager.open();
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * from korisnik";
			
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				User user = new User();
				
				user.setId(rset.getInt(1));
				user.setKorisnickoIme(rset.getString(2));
				user.setLozinka(rset.getString(3));
				user.setDatumRegistacije(rset.getString(4));
				user.setRole(Role.valueOf(rset.getString(5)));
				
				users.put(user.getKorisnickoIme(), user);
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return users;
	}
	
	
	public static boolean add(User user) throws Exception {
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO korisnik VALUES (?,?,?,?,?)";
					

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, user.getId());
			pstmt.setString(index++, user.getKorisnickoIme());
			pstmt.setString(index++, user.getLozinka());
			pstmt.setString(index++, user.getDatumRegistacije());
			pstmt.setString(index++, user.getRole().toString());

			return pstmt.executeUpdate() == 1;
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();} // ako se koristi DBCP2, konekcija se mora vratiti u pool
		}
	}
	
}
