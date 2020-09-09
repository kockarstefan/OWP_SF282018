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
	
	public static List<Movie> searchMovies (String title, String director, String genre, int durationMin, int durationMax, int dateMin, int dateMax, String distributor, String country) throws Exception {
		List<Movie> movies = new ArrayList<Movie>();
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			StringBuilder query = new StringBuilder();
			query.append("select * from Movies as M where Active = 1 ");
			if (title != null && !title.equals(""))
				query.append("and Title like '%' || ? || '%' ");
			if (director != null && !director.equals(""))
				query.append("and Director like '%' || ? || '%' ");
			if (genre != null && !genre.equals(""))
				query.append("and Genre like '%' || ? || '%' ");
			if (durationMin > 0)
				query.append("and Duration >= ? ");
			if (durationMax > 0)
				query.append("and Duration <= ? ");
			if (distributor != null && !distributor.equals(""))
				query.append("and Distributor like '%' || ? || '%' ");
			if (country != null && !country.equals(""))
				query.append("and Country like '%' || ? || '%' ");
			if (dateMin > 0)
				query.append("and ReleaseDate >= ? ");
			if (dateMax > 0)
				query.append("and ReleaseDate <= ? ");
			
			query.append("order by ID");
				
			String queryStr = query.toString();
			pstmt = connection.prepareStatement(queryStr);
			
			int index = 1;
			if (title != null && !title.equals(""))
				pstmt.setString(index++, "%" + title + "%");
			if (director != null && !director.equals(""))
				pstmt.setString(index++, "%" + director + "%");
			if (genre != null && !genre.equals(""))
				pstmt.setString(index++, "%" + genre + "%");
			if (durationMin> 0)
				pstmt.setInt(index++, durationMin);
			if (durationMax > 0)
				pstmt.setInt(index++, durationMax);
			if (distributor != null && !distributor.equals(""))
				pstmt.setString(index++, "%" + distributor + "%");
			if (country != null && !country.equals(""))
				pstmt.setString(index++, "%" + country + "%");
			if (dateMin > 0)
				pstmt.setInt(index++, dateMin);
			if (dateMax > 0)
				pstmt.setInt(index++, dateMax);
			
			
			
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				int index1 = 1;
				int id = rset.getInt(index1++);
				String filteredTitle = rset.getString(index1++);
				String filteredDirector = rset.getString(index1++);
				String filteredGenre = rset.getString(index1++);
				int filteredDuration = rset.getInt(index1++);
				String filteredDistributor = rset.getString(index1++);
				String filteredCountry = rset.getString(index1++);
				int filteredReleaseDate = rset.getInt(index1++);
				boolean active = rset.getInt(index1++) == 1;
				
				
				Movie movie = new Movie(id, filteredTitle, filteredDirector, filteredGenre, filteredDuration, filteredDistributor, filteredCountry, filteredReleaseDate, active);
				movies.add(movie);
			}
			
					
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(rset != null) try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			if(connection != null) try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return movies;
	}
	
	public static Movie getMovieByID (int mID) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select * from Movies where ID = ? ";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, mID);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
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
				
				
				return new Movie(id, title, director,genre, duration, distributor, country, releaseDate, active);
			}
			
					
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return null;
	}
	
	public static boolean addMovie (Movie movie) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Movies (Title, Director, Genre, Duration, Distributor, Country, "
					+ "ReleaseDate, Active) values (?, ?, ?, ?, ?, ?, ?, 1)";
			pstmt = connection.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, movie.getTitle());
			pstmt.setString(index++, movie.getDirector());
			pstmt.setString(index++, movie.getGenre());
			pstmt.setInt(index++, movie.getDuration());
			pstmt.setString(index++, movie.getDistributor());
			pstmt.setString(index++, movie.getCountry());
			pstmt.setInt(index++, movie.getReleaseDate());
			
			pstmt.executeUpdate();
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		return false;
	}
	
	public static boolean updateMovie (Movie movie) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			
			String query = "update Movies set Title = ?, Director = ?, Genre = ?, Duration = ?, Distributor = ?, "
					+ "Country = ?, ReleaseDate = ? where ID = ?";
			pstmt = connection.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, movie.getTitle());
			pstmt.setString(index++, movie.getDirector());
			pstmt.setString(index++, movie.getGenre());
			pstmt.setInt(index++, movie.getDuration());
			pstmt.setString(index++, movie.getDistributor());
			pstmt.setString(index++, movie.getCountry());
			pstmt.setInt(index++, movie.getReleaseDate());
			pstmt.setInt(index++, movie.getID());
			
			return pstmt.executeUpdate() == 1;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		return false;
	}
	
	public static boolean deleteMovie (Movie movie) throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		try {
			String query = "update Movies set Active = 0 where ID = ?";
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, movie.getID());
			
			return pstmt.executeUpdate() == 1;
			
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
	}
	
	public static int getNextID() throws Exception {
		ConnectionManager.open();
		Connection connection = ConnectionManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "select ifnull(max(ID), 0) + 1 from Movies";
			pstmt = connection.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				return rset.getInt(1);
			}
			
					
		} finally {
			try { pstmt.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { rset.close(); } catch (Exception e1) { e1.printStackTrace(); }
			try { connection.close(); } catch (Exception e1) { e1.printStackTrace(); }
		}
		
		return 0;
	}
	
}
