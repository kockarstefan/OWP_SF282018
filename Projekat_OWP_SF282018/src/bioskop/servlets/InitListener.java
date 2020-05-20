package bioskop.servlets;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bioskop.dao.ConnectionManager;



import model.User;


public class InitListener implements ServletContextListener {

   
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent event)  { 
    	
    	ConnectionManager.open();
    	
    
    }
	
}
