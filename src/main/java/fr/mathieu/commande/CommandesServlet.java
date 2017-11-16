package fr.mathieu.commande;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.Tools;

@WebServlet("/commandes")
@SuppressWarnings("serial")
public class CommandesServlet extends HttpServlet{
	
	@EJB
	GestionCommande gc;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		List<Commande> commandes = gc.getCommandes();
		req.setAttribute("commandes", commandes);
		this.getServletContext().getRequestDispatcher("/WEB-INF/commande/commandes.jsp").forward(req, resp);
	}
	
	

}
