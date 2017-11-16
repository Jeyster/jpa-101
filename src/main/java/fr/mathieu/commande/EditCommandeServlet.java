package fr.mathieu.commande;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.GestionTransaction;
import fr.mathieu.Tools;
import fr.mathieu.categorie.Categorie;
import fr.mathieu.user.GestionUser;

@WebServlet("/commandes/edit")
@SuppressWarnings("serial")
public class EditCommandeServlet extends HttpServlet{
	
	@EJB
	GestionCommande gc;
	
	@EJB
	GestionUser gu;
	
	@EJB
	GestionTransaction gt;
	
	private Integer commandeId;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		try{
			this.commandeId = Integer.parseInt(req.getParameter("commandeId"));
		}
		catch(NumberFormatException e){ this.commandeId = 0; }	
		
		Commande commande;
		if (this.commandeId != 0) {
			commande = gc.findCommandeById(this.commandeId);
		}
		else {
			commande = new Commande();
		}
		
		req.setAttribute("users", gu.getUsers());
		req.setAttribute("produits", gt.importProduits());
		req.setAttribute("commande", commande);
		this.getServletContext().getRequestDispatcher("/WEB-INF/commande/edit-commande.jsp").forward(req, resp);

	}
	
	

}
