package bioskop.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.UserDAO;
import model.User;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		ArrayList<User> usersList = (ArrayList<User>) context.getAttribute("usersList");
		
		request.setAttribute("usersList", usersList);
		
		RequestDispatcher rd = request.getRequestDispatcher("Users.jsp");
		
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		switch(action) {
		case "showUser": {
			String korisnickoIme = request.getParameter("korisnickoIme");
			
			try {
				request.setAttribute("user", new UserDAO().get(korisnickoIme));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("User.jsp");
			rd.forward(request, response);
			break;
			
		}
			
		}
	}

}
