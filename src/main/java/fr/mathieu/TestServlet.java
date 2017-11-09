package fr.mathieu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet("/index.html")
public class TestServlet extends HttpServlet{
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	@Resource
	private UserTransaction userTransaction;
	
	
		public List<Categorie> createCategorieList(int nbr){
			List<Categorie> categories = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				try {
					userTransaction.begin();
				} catch (NotSupportedException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Categorie c = new Categorie();
				c.setNom("categorie " + new Date());
				categories.add(c);
				try {
					em.persist(c);
					userTransaction.commit();
				} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
						| HeuristicRollbackException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return categories;
		}
		
		public List<Fabriquant> createFabriquantList(int nbr){
			List<Fabriquant> fabriquants = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				try {
					userTransaction.begin();
				} catch (NotSupportedException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Fabriquant f = new Fabriquant();
				f.setNom("fabriquant " + new Date());
				fabriquants.add(f);
				try {
					em.persist(f);
					userTransaction.commit();
				} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
						| HeuristicRollbackException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return fabriquants;
		}
		
		public List<Produit> createProduitListWithRandomFabCat(int nbr, List<Fabriquant> fabriquants, List<Categorie> categories){
			List<Produit> produits = new ArrayList<>();
			for (int i = 0; i < nbr; i++) {
				Produit p = new Produit();
				p.setNom("produit " + new Date());
				Categorie c = categories.get((int) (Math.random() * 10));
				Fabriquant f = fabriquants.get((int) (Math.random() * 10));//new  Random().nextInt(categories.size()))
				p.setCategorie(c);
				p.setFabriquant(f);
				produits.add(p);
			}
			return produits;
		}
		
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
				List<Categorie> categories = createCategorieList(10);
				List<Fabriquant> fabriquants = createFabriquantList(10);
				List<Produit> produits = new ArrayList<>();
				int nbrProduits = 100;
				for (int i = 0; i < nbrProduits ;i++) {
					Produit p = new Produit();
					p.setNom("produit " + new Date());
					try {
						userTransaction.begin();
					} catch (NotSupportedException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Categorie c = em.merge(categories.get((int) (Math.random() * 10)));
					Fabriquant f = em.merge(fabriquants.get((int) (Math.random() * 10)));
					p.setCategorie(c);
					p.setFabriquant(f);
					p.setReference(UUID.randomUUID().toString());
					em.persist(p);
					try {
						userTransaction.commit();
					} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
							| HeuristicRollbackException | SystemException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					produits.add(p);
				}
				
				String message = nbrProduits + " produits ont été créés et ajoutés à la base de données !";
				req.setAttribute("test", message);
				this.getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(req, resp);
		}
		
	
}
