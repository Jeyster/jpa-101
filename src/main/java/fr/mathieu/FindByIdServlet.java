package fr.mathieu;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/find")
public class FindByIdServlet extends HttpServlet{
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	
	@Resource
	private UserTransaction userTransaction;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			userTransaction.begin();
			Produit p = em.find(Produit.class, id);
			System.out.println(p.getNom() + " " + p.getCategorie().getNom() + " " + p.getFabriquant().getNom());
			//em.remove(p);
			userTransaction.commit();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
