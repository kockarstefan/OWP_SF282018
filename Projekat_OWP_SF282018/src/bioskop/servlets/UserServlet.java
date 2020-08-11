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


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			String loggedInUsername = (String) request.getSession().getAttribute("loggedInUsername");
//			if (loggedInUsername == null) {
//				request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
//				return;
//			}
//			User loggedInUser = UserDAO.getUserByUsername(loggedInUsername);
//			if (loggedInUser == null) {
//				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
//				return;
//			}
//			
			//if (!loggedInUser.isLoggedIn()) {
				//request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				//return;
			//}
			
			//if (loggedInUser.getUserRole() != UserRole.ADMIN && !loggedInUser.getUsername().equals(request.getParameter("username"))) {
			//	request.getRequestDispatcher("./UnauthorizedServlet").forward(request, response);
			//	return;
			//}
			
			String username = request.getParameter("userName");
			User user = UserDAO.getUserByUsername(username);
			
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("user", user);
			
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
