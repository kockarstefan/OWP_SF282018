package bioskop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.MovieDAO;
import bioskop.dao.UserDAO;
import javafx.scene.chart.PieChart.Data;
import model.Movie;

@SuppressWarnings("serial")
public class MoviesServlet extends HttpServlet {
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Movie> movies = new ArrayList<>();
			movies = MovieDAO.getMovies();
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("movies", movies);
			
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
		switch(action) {
		case "add": {
			try {
				String title = request.getParameter("title");
				String director = request.getParameter("director");
				String genre = request.getParameter("genre");
				int duration = Integer.parseInt(request.getParameter("duration"));
				String distributor = request.getParameter("distributor");
				String country = request.getParameter("country");
				int releaseDate = Integer.parseInt(request.getParameter("releaseDate"));
				
				Movie movie = new Movie(MovieDAO.getNextID(), title, director, genre,
						duration, distributor, country, releaseDate, true);
				
				if (!MovieDAO.addMovie(movie))
					throw new Exception("Greska prilikom dodavanja filma u bazu");
				
				break;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		case "update": {
			try {
				String stringId = request.getParameter("id");
				int id = Integer.parseInt(stringId);
				Movie movie = MovieDAO.getMovieByID(id);
				String title = request.getParameter("title");
				String director = request.getParameter("director");
				String genre = request.getParameter("genre");
				int duration = Integer.parseInt(request.getParameter("duration"));
				String distributor = request.getParameter("distributor");
				String country = request.getParameter("country");
				int releaseDate = Integer.parseInt(request.getParameter("releaseDate"));
				
				movie.setID(id);
				movie.setTitle(title);
				movie.setDirector(director);
				movie.setGenre(genre);
				movie.setDuration(duration);
				movie.setDistributor(distributor);
				movie.setCountry(country);
				movie.setReleaseDate(releaseDate);
				
				if (movie != null) {
					if (!MovieDAO.updateMovie(movie))
						throw new Exception("Greska prilikom izmene filma u bazi");
				}
				break;
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		case "delete": {
			try {
				String stringId = request.getParameter("id");
				int id = Integer.parseInt(stringId);
				Movie movie = MovieDAO.getMovieByID(id);
				if (movie != null) {
					if (!MovieDAO.deleteMovie(movie))
						throw new Exception("Greska prilikom brisanja filma iz baze");
				
				}
				
			break;
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			}
		}
		
		request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch (Exception ex) {
			ex.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
		
		}
				
		
}

