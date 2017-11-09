package fr.mathieu;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/find")
@SuppressWarnings("serial")
public class FindByIdServlet extends HttpServlet{

	@EJB
	private GestionTransaction gt;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		Produit p = new Produit();
		p = gt.findById(id);
		
		req.setAttribute("produit", p);
		this.getServletContext().getRequestDispatcher("/WEB-INF/find-by-id.jsp").forward(req, resp);
		
	}
	
	

}
