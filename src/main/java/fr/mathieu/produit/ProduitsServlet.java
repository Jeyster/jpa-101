package fr.mathieu.produit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.GestionTransaction;


/* SERVLET
 * -----------
 * Importe depuis BS la liste des Fabricant
 * Permet leur affichage et leur gestion par HTML
 * 
 *  TODO : nécessite l'existence de la liste de Categorie en changant l'URL index.html
 *  		affficher un message d'erreur perso */

@WebServlet("/produits")
@SuppressWarnings("serial")
public class ProduitsServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myCategorieId = 0;
		int myFabricantId = 0;
		
		try {
			myCategorieId = Integer.parseInt(req.getParameter("categorieId"));
		} catch (NumberFormatException e) {};
		try {
			myFabricantId = Integer.parseInt(req.getParameter("fabricantId"));
		} catch (NumberFormatException e) {};	
		
		List<Produit> produits = new ArrayList<>();
		if (myCategorieId != 0) {
			produits = gt.findCategorieProduits(myCategorieId);
		}
		else if (myFabricantId != 0) {
			produits = gt.findFabricantProduits(myFabricantId);
		}
		else {
			produits = gt.importProduits();
		}
		req.setAttribute("produits",produits);
		this.getServletContext().getRequestDispatcher("/WEB-INF/produit/produits.jsp").forward(req, resp);
	}
}
