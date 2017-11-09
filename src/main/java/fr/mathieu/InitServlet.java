package fr.mathieu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.categorie.Categorie;
import fr.mathieu.fabricant.Fabricant;
import fr.mathieu.produit.Produit;

@WebServlet("/index.html")
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	@EJB
	private GestionTransaction gt;

	/* Renvoie une liste de Categorie créées aléatoirement et ajoutées à la BD */
	public List<Categorie> createCategorieList(int nbr) {
		List<Categorie> categories = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			Categorie categorie = new Categorie();
			categorie.setNom("categorie " + new Date());
			categories.add(gt.addCategorie(categorie));
		}
		return categories;
	}

	/* Renvoie une liste de Fabricant créées aléatoirement et ajoutées à la BD */
	public List<Fabricant> createFabriquantList(int nbr) {
		List<Fabricant> fabricants = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			Fabricant fabricant = new Fabricant();
			fabricant.setNom("fabriquant " + new Date());
			fabricant.setAdresse("adresse " + new Date());
			fabricants.add(gt.addFabricant(fabricant));
		}
		return fabricants;
	}

	/*
	 * Renvoie une liste de Produit créées aléatoirement et ajoutées à la BD
	 * Fabricant et Categorie attribués aléatoirement parmis ceux existants
	 */
	public List<Produit> createProduitListWithRandomFabCat(int nbr, List<Fabricant> fabricants,
			List<Categorie> categories) {
		List<Produit> produits = new ArrayList<>();
		for (int i = 0; i < nbr; i++) {
			Produit p = new Produit();
			p.setNom("produit " + new Date());
			Categorie c = categories.get((int) (Math.random() * categories.size()));
			Fabricant f = fabricants.get((int) (Math.random() * fabricants.size()));// new
																					// Random().nextInt(categories.size()))
			p.setCategorie(c);
			p.setFabricant(f);
			p.setReference(UUID.randomUUID().toString());
			produits.add(gt.addProduit(p));
		}
		return produits;
	}

	/*
	 * GET 
	 * ------------- 
	 * Initialise la BD avec un nombre nbrProduit de Produit aléatoires. 
	 * Importe listes de Categorie et Fabricant si existantes, 
	 * sinon en créer 10 de chaque pour pouvoir créer les produits.
	 * -------------
	 * Redirige vers init.jsp
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Categorie> categories = gt.importCategories();
		if (categories.isEmpty()) {
			categories = new ArrayList<>();
			categories = this.createCategorieList(10);
		}
		List<Fabricant> fabricants = gt.importFabricants();
		if (fabricants.isEmpty()) {
			fabricants = new ArrayList<>();
			fabricants = this.createFabriquantList(10);
		}
		int nbrProduit = 100;
		this.createProduitListWithRandomFabCat(nbrProduit, fabricants, categories);

		req.setAttribute("nbrProduit", nbrProduit);
		this.getServletContext().getRequestDispatcher("/WEB-INF/init.jsp").forward(req, resp);
	}

}
