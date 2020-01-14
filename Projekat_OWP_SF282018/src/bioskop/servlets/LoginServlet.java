package bioskop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.UserDAO;
import model.User;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");

		try {
			User user = UserDAO.get(korisnickoIme, lozinka);
			if (user == null) {
				response.sendRedirect("./Index.jsp");
				return;
			}

			request.getSession().setAttribute("loggedInUser", user);
			
			response.sendRedirect("./FilmServlet");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
