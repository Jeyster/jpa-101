package fr.mathieu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.html")
@SuppressWarnings("serial")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("!!! User Session : " + req.getSession().getAttribute("user") + " !!!");
		
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		else{
			resp.sendRedirect("main.jsp");
		}
	}

	
	
}
