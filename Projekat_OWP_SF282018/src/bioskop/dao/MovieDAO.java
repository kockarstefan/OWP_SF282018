package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Movie;
import model.User;
import util.DateTimeUtil;

public class MovieDAO {

	public static List<Movie> getMovies() throws Exception {
		
		List<Movie> movies = new ArrayList<>();
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			String query = "select * from Movies where Active = 1";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String title = rset.getString(index++);
				String director = rset.getString(index++);
				String genre = rset.getString(index++);
				int duration = rset.getInt(index++);
				String distributor = rset.getString(index++);
				String country = rset.getString(index++);
				int releaseDate = rset.getInt(index++);
				boolean active = rset.getInt(index++) == 1;
				
				Movie movie = new Movie(id,title, director, genre, duration, distributor, country, releaseDate, active);
				
				movies.add(movie);
				
			}
		}finally {
			if(pstmt != null) try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(rset != null) try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return movies;
	}
	
	
}
