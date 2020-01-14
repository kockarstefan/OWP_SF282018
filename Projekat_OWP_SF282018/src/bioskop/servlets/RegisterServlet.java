package bioskop.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bioskop.dao.UserDAO;
import model.User;
import model.User.Role;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		Map<String, User> users = (Map<String, User>) context.getAttribute("users");
		
		try {
			String korisnickoIme = request.getParameter("korisnickoIme");
			if (users.containsKey(korisnickoIme))
				throw new Exception("Korisnicko ime vec postoji!");
			if ("".equals(korisnickoIme))
				throw new Exception("Korisnicko ime je prazno!");

			String lozinka = request.getParameter("lozinka");
			if ("".equals(lozinka))
				throw new Exception("Lozinka je prazna!");

			String ponovljenaLozinka = request.getParameter("ponovljenaLozinka");
			if (!lozinka.equals(ponovljenaLozinka))
				throw new Exception("Lozinke se ne podudaraju!");
			
			int id = users.size() + 1;
			
			String datumReg = request.getParameter("datum");
			
			User user = new User(id, korisnickoIme, lozinka, datumReg, Role.Korisnik);
			UserDAO.add(user);

			response.sendRedirect("./Index.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
