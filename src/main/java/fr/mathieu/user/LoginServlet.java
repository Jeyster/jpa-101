package fr.mathieu.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.Tools;

@WebServlet("/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	
	@EJB
	private GestionUser gu;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String login = req.getParameter("login");
		String password = req.getParameter("password");
		boolean knownUser = true;
		
		User user = gu.login(login, password);	
		
		if (user == null) {
			knownUser = false;
			req.setAttribute("knownUser", knownUser);
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		else {
			Tools.setLoggedInUser(req.getSession(), user);
			resp.sendRedirect("main.jsp");	
		}
				
	}
	
	

}
