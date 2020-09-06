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
import model.Movie;
import model.User;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<User> users = new ArrayList<>();
			users = UserDAO.getAllUsers();
			Map<String, Object> data = new LinkedHashMap<String, Object>();
			data.put("users", users);
			
			request.setAttribute("data", data);
			request.getRequestDispatcher("./SuccessServlet").forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		switch (action) {
			case "update": {
				try {
				User user = UserDAO.getUserByUsername(request.getParameter("username"));
				String realPassword = UserDAO.getPasswordByUsername(request.getParameter("username"));
				String newPassword = request.getParameter("newPassword");
				if (!realPassword.equals(newPassword))
					user.setPassword(newPassword);
				else
					user.setPassword(realPassword);
				
				User.Role newUserRole = User.Role.valueOf(request.getParameter("newUserRole"));
				if (user.getRole() != newUserRole)
					user.setRole(newUserRole);
				
				if (user != null) {
					if (!UserDAO.updateUser(user))
						throw new Exception("Greska prilikom izmene korisnika u bazi");
				}
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				break;
			}catch(Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("./FailureServlet").forward(request, response);
			}
			}
			case "delete": {
				User user;
				try {
					user = UserDAO.getUserByUsername(request.getParameter("username"));
				
				
				if (user != null) {
					if (!UserDAO.deleteUser(user))
						throw new Exception("Greska prilikom brisanja korisnika iz baze");
					
				}
				request.getRequestDispatcher("./SuccessServlet").forward(request, response);
				break;
				}catch(Exception e) {
					e.printStackTrace();
					request.getRequestDispatcher("./FailureServlet").forward(request, response);
				}
				
			}
	}
}
}
