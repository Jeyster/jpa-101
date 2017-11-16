package fr.mathieu.produit;

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
import fr.mathieu.fabricant.Fabricant;


/* SERVLET
 * -----------
 * Edition d'une Categorie (création ou modification du nom) */

@WebServlet("/produits/edit")
@SuppressWarnings("serial")
public class EditProduitServlet extends HttpServlet {
	
	@EJB
	private GestionTransaction gt;
	
	/* Attribut permettant de garder sur toute la session (et donc entre le GET et le POST)
	 * l'id d'une catégorie choisie par le GET */
	private int produitId;

	
	/*
	 * GET 
	 * ------------- 
	 * Request Parameter 'clickedId'
	 * -------------
	 * Si requête vient d'un form souhaitant l'édition d'une Categorie existante (clickedID != 0),
	 * celle-ci est récupérée dans la BD grâce à son Id donné en paramètre.
	 * Sinon (clickedId = 0), une Categorie et crée.
	 * La Categorie est ensuite envoyée vers 'edit-categorie.jsp'.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		try {
			this.produitId = Integer.parseInt(req.getParameter("produitId"));
		}catch(NumberFormatException e) {
			this.produitId = 0;
		}
		
		Produit produit;
		if (this.produitId != 0) {
			produit = gt.findProduitById(this.produitId);
		}
		else {
			produit = new Produit();
		}
		req.setAttribute("produit", produit);
		req.setAttribute("produitId", this.produitId);
		req.setAttribute("categories", gt.importCategories());
		req.setAttribute("fabricants", gt.importFabricants());
		this.getServletContext().getRequestDispatcher("/WEB-INF/produit/edit-produit.jsp").forward(req, resp);
	}
	
	
	/*
	 * POST
	 * ------------- 
	 * Request Parameter 'name'
	 * -------------
	 * Si requête vient d'un form souhaitant l'édition d'une Categorie existante (myCategorieId != 0),
	 * celle-ci est récupérée dans la BD grâce à son Id gardé en mémoire dans myCategorieId suite au GET.
	 * Sinon (myCategorieId = 0), une Categorie et crée.
	 * Le nom de la Categorie est ensuite modifié dans la BD
	 * -------------
	 * Redirection vers CategoriesServlet
	 * -------------
	 * ATTENTION : Le GET détermine le POST par la valeur de myCategorieId
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		String name = req.getParameter("name");
		String ref = req.getParameter("ref");
		int categorieId = Integer.parseInt(req.getParameter("categorieId"));
		Categorie categorie = gt.findCategorieById(categorieId);
		int fabricantId = Integer.parseInt(req.getParameter("fabricantId"));
		Fabricant fabricant = gt.findFabricantById(fabricantId);
		Produit produit;
		if (this.produitId != 0) {
			produit = gt.findProduitById(produitId);
		}
		else {
			produit = new Produit();
		}
		gt.modifyProduit(produit, name, ref, categorie, fabricant);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits");
	}
	
	
	
	

}
