package fr.mathieu.produit;

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
 * Trouver dans BD le Produit d'id entré en queryParam
 * input : queryParam id (?id=idVoulu dans url) */

@WebServlet("/find-produit")
@SuppressWarnings("serial")
public class FindProduitByIdServlet extends HttpServlet{

	@EJB
	private GestionTransaction gt;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		Produit p = gt.findProduitById(id);
		
		req.setAttribute("produit", p);
		this.getServletContext().getRequestDispatcher("/WEB-INF/produit/find-produit-by-id.jsp").forward(req, resp);
		
	}
	
	

}
