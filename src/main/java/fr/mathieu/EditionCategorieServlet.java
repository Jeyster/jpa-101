package fr.mathieu;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categories/edition")
@SuppressWarnings("serial")
public class EditionCategorieServlet extends HttpServlet {
	
	@EJB
	private GestionTransaction gt;
	
	private int myCategorieId = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.myCategorieId = Integer.parseInt(req.getParameter("clickedId"));
		Categorie categorie;
		if (this.myCategorieId != 0) {
			categorie = gt.findByIdCategorie(this.myCategorieId);
		}
		else {
			categorie = new Categorie();
		}
		req.setAttribute("categorie", categorie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/edition-categorie.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Categorie categorie;
		if (this.myCategorieId != 0) {
			categorie = gt.findByIdCategorie(myCategorieId);
		}
		else {
			categorie = new Categorie();
		}
		gt.modifierNomCategorie(name, categorie);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
	}
	
	
	
	

}
