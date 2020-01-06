package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Films;

public class FilmDAO {

	public Film get(String naziv) throws Exception {
		
		ConnectionManager.open();
		Connection conn = ConnectionManager.getConnection();
		
		Film film = new Film();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT naziv,trajanje,distributer,zemlja_por, god_proiz FROM film WHERE naziv =?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naziv);
			
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				film.setNaziv(rset.getString(index++));
				film.setTrajanjeFilma(rset.getString(index++));
				film.setDistributer(rset.getString(index++));
				film.setZemljaPorekla(rset.getString(index++));;
				int godinaProizvodnje = rset.getInt(index++);
				film.setGodinaProizvodnje(godinaProizvodnje);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		return film;
	}
	
public ArrayList<Films> getAllFilms() throws Exception {
		
		ArrayList<Films> films = new ArrayList<>();
		
		ConnectionManager.open();
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT naziv, trajanje, distributer, zemlja_por, god_proiz FROM film";

			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				
				Films film = new Films();
				
				film.setNaziv(rset.getString(1));
				film.setTrajanjeFilma(rset.getString(2));
				film.setDistributer(rset.getString(3));
				film.setZemljaPorekla(rset.getString(4));
				film.setGodinaProizvodnje(rset.getInt(5));
				
				films.add(film);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
	
		return films;
	}
	
}
