package bioskop.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.UserDAO;
import model.User;

/**
 * Servlet implementation class LoggedInUserServlet
 */
public class LoggedInUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoggedInUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedInUsername = (String) request.getSession().getAttribute("loggedInUsername");
		if (loggedInUsername == null) {
			request.getRequestDispatcher("./UnauthenticatedServlet").forward(request, response);
			return;
		}
		try {
			
			User user = UserDAO.getUserByUsername(loggedInUsername);
			if (user == null) {
				request.getSession().invalidate();
				request.getRequestDispatcher("./UnauthenticatedServlet").forward(request, response);
				return;
			}
			
			
			
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			Map<String, Object> loggedInUser = new LinkedHashMap<String, Object>();
			
			loggedInUser.put("username", user.getUsername());
			loggedInUser.put("userRole", user.getRole().toString());
			
			data.put("loggedInUser", loggedInUser);
			
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("./FailureServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
