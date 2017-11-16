package fr.mathieu.fabricant;

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
 * Importe depuis BS la liste des Fabricant
 * Permet leur affichage et leur gestion par HTML
 * 
 *  TODO : nécessite l'existence de la liste de Categorie en changant l'URL index.html
 *  		affficher un message d'erreur perso */

@WebServlet("/fabricants")
@SuppressWarnings("serial")
public class FabricantsServlet extends HttpServlet{
	
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
		
		List<Fabricant> fabricants = new ArrayList<>();
		if ((searchedName != "") && (searchedName != null)) {
			fabricants = gt.findFabricantsByName(searchedName);
		}
		else {
			fabricants = gt.importFabricants();
		}
		req.setAttribute("fabricants",fabricants);
		this.getServletContext().getRequestDispatcher("/WEB-INF/fabricant/fabricants.jsp").forward(req, resp);
	}
}
