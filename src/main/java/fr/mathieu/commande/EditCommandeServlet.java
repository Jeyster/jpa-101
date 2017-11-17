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
import fr.mathieu.produit.Produit;
import fr.mathieu.user.GestionUser;
import fr.mathieu.user.User;

@WebServlet("/commandes/edit")
@SuppressWarnings("serial")
public class EditCommandeServlet extends HttpServlet{
	
	@EJB
	private GestionCommande gc;
	
	@EJB
	private GestionUser gu;
	
	@EJB
	private GestionTransaction gt;
	
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
		
		req.setAttribute("commandeId", this.commandeId);
		req.setAttribute("users", gu.getUsers());
		req.setAttribute("produits", gt.importProduits());
		req.setAttribute("commande", commande);
		this.getServletContext().getRequestDispatcher("/WEB-INF/commande/edit-commande.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try{
			this.commandeId = Integer.parseInt(req.getParameter("commandeId"));
		}
		catch(NumberFormatException e){ this.commandeId = 0; }	
		
		int userId = 0;
		Integer produitId = 0;
		Integer quantity = 0;
		
		try {
			userId = Integer.parseInt(req.getParameter("userId"));
		}
		catch(NumberFormatException e) {}
		
		try {
			produitId = Integer.parseInt(req.getParameter("produitId"));
		}
		catch(NumberFormatException e) {}
		
		try {
			quantity = Integer.parseInt(req.getParameter("quantity"));
		}
		catch(NumberFormatException e) {}
		
		User user = gu.findUserById(userId);
		Produit produit = gt.findProduitById(produitId);
		
		Commande commande;
		if (this.commandeId != 0) {
			commande = gc.findCommandeById(this.commandeId);
		}
		else {
			commande = new Commande();
		}
		commande.setUser(user);
		commande.setProduit(produit);
		commande.setQuantity(quantity);
		gc.addCommande(commande);
		
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/commandes");
		
	}
	
	
	
	

}
