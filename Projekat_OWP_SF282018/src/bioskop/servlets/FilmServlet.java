package bioskop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.ConnectionManager;
import bioskop.dao.FilmDAO;
import model.Film;
import model.Films;

/**
 * Servlet implementation class FilmServlet
 */
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		ServletContext context = getServletContext();
		
		
		try {
			request.setAttribute("films", new FilmDAO().getAllFilms());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("Films.jsp");
		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		ServletContext context = getServletContext();
		ArrayList<Films> films = (ArrayList) context.getAttribute("films");
 		
		String action = request.getParameter("action");
		switch(action) {
		case "add" : {
			
			int filmsCount = films.size();
			
			int idADD = filmsCount + 1;
			String reziserADD = (String) request.getParameter("reziser");
			String nazivADD = (String) request.getParameter("naziv");
			String trajanjeADD = (String) request.getParameter("trajanje");
			String distributerADD = (String) request.getParameter("distributer");
			String zemljaPoreklaADD = (String) request.getParameter("zemljaPorekla");
			int godinaProizvodnjeADD = Integer.parseInt(request.getParameter("godinaProizvodnje"));
			
			Film filmADD = new Film(idADD, reziserADD, nazivADD, trajanjeADD, distributerADD, zemljaPoreklaADD, godinaProizvodnjeADD);
			
			try {
				FilmDAO.addFilm(filmADD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Films filmsADD = new Films(nazivADD, trajanjeADD, distributerADD, zemljaPoreklaADD, godinaProizvodnjeADD);
			
			films.add(filmsADD);
				
			request.setAttribute("films", films);
			RequestDispatcher rd = request.getRequestDispatcher("Films.jsp");
			rd.forward(request, response);
			break;
		}
		case "showFilm": {
			
			String naziv = request.getParameter("naziv");
			
			try {
				request.setAttribute("film", new FilmDAO().get(naziv));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("Film.jsp");
			rd.forward(request, response);
			break;
		}
		
		
	}

	}
}
