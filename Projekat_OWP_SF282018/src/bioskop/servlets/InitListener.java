package bioskop.servlets;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bioskop.dao.ConnectionManager;
import bioskop.dao.FilmDAO;
import bioskop.dao.UserDAO;
import model.Film;
import model.Films;
import model.User;


public class InitListener implements ServletContextListener {

   
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	ConnectionManager.open();
    	
    	ServletContext context = event.getServletContext();
    	
    	try {
			ArrayList<User> usersList = new UserDAO().getUsersList();
			context.setAttribute("usersList", usersList);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
    	
    	try {
			Map<String, User> users = new UserDAO().getAllUsers();
			context.setAttribute("users", users);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			ArrayList<Films> films = new FilmDAO().getAllFilms();
			context.setAttribute("films", films);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    
    }
	
}
