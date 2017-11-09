package fr.mathieu.servlets;

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

import fr.mathieu.Categorie;
import fr.mathieu.Fabricant;
import fr.mathieu.GestionTransaction;
import fr.mathieu.Produit;

@WebServlet("/index.html")
@SuppressWarnings("serial")
public class TestServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;
	
	
		public List<Categorie> createCategorieList(int nbr){
			List<Categorie> categories = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				categories.add(gt.ajouterCategorie());
			}
			return categories;
		}
		
		public List<Fabricant> createFabriquantList(int nbr){
			List<Fabricant> fabricants = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				fabricants.add(gt.ajouterFabricant());
			}
			return fabricants;
		}
		
		public List<Produit> createProduitListWithRandomFabCat(int nbr, List<Fabricant> fabricants, List<Categorie> categories){
			List<Produit> produits = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				Produit p = new Produit();
				p.setNom("produit " + new Date());
				Categorie c = categories.get((int) (Math.random() * categories.size()));
				Fabricant f = fabricants.get((int) (Math.random() * categories.size()));//new  Random().nextInt(categories.size()))
				p.setCategorie(c);
				p.setFabricant(f);
				produits.add(p);
			}
			return produits;
		}
		
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
				List<Categorie> categories = gt.importCategories();
				if (categories.isEmpty()) {
					categories = new ArrayList<>();
					categories = createCategorieList(10);
				}
				List<Fabricant> fabricants = gt.importFabricants();
				if (fabricants.isEmpty()) {
					fabricants = new ArrayList<>();
					fabricants = createFabriquantList(10);
				}
				List<Produit> produits = new ArrayList<>();
				int nbrProduits = 100;
				for (int i = 0; i < nbrProduits ;i++) {
					Produit p = new Produit();
					p.setNom("produit " + new Date());
					Categorie c = categories.get((int) (Math.random() * categories.size()));
					Fabricant f = fabricants.get((int) (Math.random() * categories.size()));
					p.setCategorie(c);
					p.setFabricant(f);
					p.setReference(UUID.randomUUID().toString());
					produits.add(gt.ajouterProduit(p));
				}
				
				String message = nbrProduits + " produits ont été créés et ajoutés à la base de données !";
				req.setAttribute("test", message);
				this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
		}
		
	
}
