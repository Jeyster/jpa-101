package fr.mathieu.commande;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.GestionTransaction;


/* SERVLET
 * -----------
 * Supprime de la BD la Categorie d'id entré donné en paramètre de la requête 
 * (queryParam ou parmètre soumis par un form dans .jsp)
 * Redirige vers CategoriesServlet */

@WebServlet("/commandes/delete")
@SuppressWarnings("serial")
public class DeleteCommandeServlet extends HttpServlet {

	@EJB
	private GestionCommande gc;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int commandeId = Integer.parseInt(req.getParameter("commandeId"));		
		gc.removeByIdCommande(commandeId);
		
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/commandes");
	}
	
	

}
