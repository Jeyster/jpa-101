package fr.mathieu;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet("/index.html")
public class TestServlet extends HttpServlet{
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	@Resource
	private UserTransaction userTransaction;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
		userTransaction.begin();
		Categorie c = new Categorie();
		c.setNom("toto" + new Date());
		em.persist(c);
		userTransaction.commit();
		}
		catch(Exception e) {}
	}

	
	
}
