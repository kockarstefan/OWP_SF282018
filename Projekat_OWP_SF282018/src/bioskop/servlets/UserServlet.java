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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
