package bioskop.servlets;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bioskop.dao.ConnectionManager;
import bioskop.dao.FilmDAO;
import model.Film;
import model.Films;


public class InitListener implements ServletContextListener {

   
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	ConnectionManager.open();
    	
    	ServletContext context = event.getServletContext();
    	
		try {
			ArrayList<Films> films = new FilmDAO().getAllFilms();
			context.setAttribute("films", films);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    
    }
	
}
