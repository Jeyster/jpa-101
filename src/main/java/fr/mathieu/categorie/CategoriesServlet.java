package fr.mathieu.categorie;

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
import fr.mathieu.Tools;


/* SERVLET
 * -----------
 * Importe depuis BS la liste des Categorie
 * Permet leur affichage et leur gestion par HTML
 * 
 *  TODO : nécessite l'existence de la liste de Categorie en changant l'URL index.html
 *  		affficher un message d'erreur perso */

@WebServlet("/categories")
@SuppressWarnings("serial")
public class CategoriesServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		String searchedName = "";
		try {
			searchedName = req.getParameter("searchedName");
		} catch (NullPointerException e) {
			searchedName = "";
		};
		
		List<Categorie> categories = new ArrayList<>();
		if ((searchedName != "") && (searchedName != null)) {
			categories = gt.findCategoriesByName(searchedName);
		}
		else {
			categories = gt.importCategories();
		}
		req.setAttribute("categories",categories);
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie/categories.jsp").forward(req, resp);
	}
}
