package fr.mathieu.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.Tools;

@WebServlet("/logout")
@SuppressWarnings("serial")
public class LogOutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Tools.setLoggedOutUser(req.getSession());
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
	}
	
	

}
