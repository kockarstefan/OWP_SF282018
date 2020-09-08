package bioskop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.UserDAO;
import model.User;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
       
   
    public LogoutServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("loggedInUsername");
		System.out.println(username);
		
		try {
			User user = UserDAO.getUserByUsername(username);
			
			if(user != null) {
				UserDAO.logout(user);
				request.getSession().invalidate();
				request.getRequestDispatcher("./AuthenticationServlet").forward(request, response);
				
			} else {
				request.getSession().invalidate();
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
