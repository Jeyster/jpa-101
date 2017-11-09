package fr.mathieu;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categories")
@SuppressWarnings("serial")
public class CategoriesServlet extends HttpServlet{
	
	@EJB
	private GestionTransaction gt;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Categorie> categories = gt.importCategories();
		req.setAttribute("categories",categories);
		this.getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(req, resp);
	}
}
