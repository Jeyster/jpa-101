package fr.mathieu.user;

import fr.mathieu.user.GestionUser;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@WebServlet("/subscribe")
@SuppressWarnings("serial")
public class SubscribeServlet extends HttpServlet{
	
	@EJB
	private GestionUser gu;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lastname;
		String firstname;
		String login;
		String password;
		boolean alreadyExist = false;
		
		try{
			lastname = req.getParameter("lastname");
		}
		catch(NumberFormatException | NullPointerException e){
			lastname = "";
		}
		
		try{
			firstname = req.getParameter("firstname");
		}
		catch(NumberFormatException | NullPointerException e){
			firstname = "";
		}
		
		try{
			login = req.getParameter("login");
		}
		catch(NumberFormatException | NullPointerException e){
			login = "";
		}
		
		try{
			password = req.getParameter("password");
		}
		catch(NumberFormatException | NullPointerException e){
			password = "";
		}
		
		User user = new User();
		user.setLastname(lastname);
		user.setFirstname(firstname);
		user.setLogin(login);
		user.setPassword(password);
		
		try{
			gu.addUser(user);
			req.setAttribute("login", login);
			req.setAttribute("password", password);
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		catch(ConstraintViolationException | PersistenceException | EJBException e){
			alreadyExist = true;
			req.setAttribute("exist", alreadyExist);
			this.getServletContext().getRequestDispatcher("/subscribe.jsp").forward(req, resp);
		}
		
	}

}
