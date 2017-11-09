package fr.mathieu.categorie;

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
 * Trouver dans BD la Categorie d'id entré en queryParam
 * input : queryParam id (?id=idVoulu dans url) */

@WebServlet("/find-categorie")
@SuppressWarnings("serial")
public class FindCategorieByIdServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));		
		Categorie categorie = gt.findCategorieById(id);
		
		req.setAttribute("categorie",categorie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/categorie/find-categorie-by-id.jsp").forward(req, resp);
	}

}
