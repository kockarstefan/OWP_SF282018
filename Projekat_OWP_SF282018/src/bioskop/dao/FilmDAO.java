package bioskop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Film;
import model.Films;

public class FilmDAO {

	public static Film get(int id) throws Exception {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT naziv,trajanje,distributer,zemlja_por, god_proiz FROM film WHERE id =?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				int index = 1;
				String naziv = rset.getString(index++);
				String reziser = rset.getString(index++);
				String trajanje = rset.getString(index++);
				String distributer = rset.getString(index++);
				String zemljaPorekla = rset.getString(index++);
				int godinaProizvodnje = rset.getInt(index++);
				
				return new Film(id, naziv, reziser, trajanje, distributer, zemljaPorekla, godinaProizvodnje);
			}
		} finally {
			try {pstmt.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (Exception ex1) {ex1.printStackTrace();}
			try {conn.close();} catch (Exception ex1) {ex1.printStackTrace();}
		}
		
		
		return null;
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
