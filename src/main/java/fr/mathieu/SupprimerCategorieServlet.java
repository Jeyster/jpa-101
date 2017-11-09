package fr.mathieu;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categories/supprimer")
@SuppressWarnings("serial")
public class SupprimerCategorieServlet extends HttpServlet {

	@EJB
	private GestionTransaction gt;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("clickedId"));		
		Categorie categorie = gt.removeByIdCategorie(id);
		
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
		/*
		req.setAttribute("categorie", categorie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/find-by-id-categorie.jsp").forward(req, resp);
		*/
	}
	
	

}
