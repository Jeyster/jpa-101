package fr.mathieu.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mathieu.Categorie;
import fr.mathieu.GestionTransaction;

@WebServlet("/find-categorie")
@SuppressWarnings("serial")
public class CategorieProduitsServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));		
		Categorie categorie = gt.findByIdCategorie(id);
		
		req.setAttribute("categorie",categorie);
		this.getServletContext().getRequestDispatcher("/WEB-INF/find-by-id-categories.jsp").forward(req, resp);
	}

}
