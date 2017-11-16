package fr.mathieu.fabricant;

import java.io.IOException;

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
 * Edition d'une Categorie (création ou modification du nom) */

@WebServlet("/fabricants/edit")
@SuppressWarnings("serial")
public class EditFabricantServlet extends HttpServlet {
	
	@EJB
	private GestionTransaction gt;
	
	/* Attribut permettant de garder sur toute la session (et donc entre le GET et le POST)
	 * l'id d'une catégorie choisie par le GET */
	private int myFabricantId;

	
	/*
	 * GET 
	 * ------------- 
	 * Request Parameter 'clickedId'
	 * -------------
	 * Si requête vient d'un form souhaitant l'édition d'une Categorie existante (clickedID != 0),
	 * celle-ci est récupérée dans la BD grâce à son Id donné en paramètre.
	 * Sinon (clickedId = 0), une Categorie et crée.
	 * La Categorie est ensuite envoyée vers 'edit-categorie.jsp'.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		int produitId;
		try{
			produitId = Integer.parseInt(req.getParameter("produitId"));
		}
		catch(NumberFormatException e){ produitId = -1; }
		
		try{
			this.myFabricantId = Integer.parseInt(req.getParameter("categorieId"));
		}
		catch(NumberFormatException e){ this.myFabricantId = 0; }			
		Fabricant fabricant;
		if (this.myFabricantId != 0) {
			fabricant = gt.findFabricantById(this.myFabricantId);
		}
		else {
			fabricant = new Fabricant();
		}
		
		req.setAttribute("produitId", produitId);
		req.setAttribute("fabricant", fabricant);
		this.getServletContext().getRequestDispatcher("/WEB-INF/fabricant/edit-fabricant.jsp").forward(req, resp);
	}
	
	
	/*
	 * POST
	 * ------------- 
	 * Request Parameter 'name'
	 * -------------
	 * Si requête vient d'un form souhaitant l'édition d'une Categorie existante (myCategorieId != 0),
	 * celle-ci est récupérée dans la BD grâce à son Id gardé en mémoire dans myCategorieId suite au GET.
	 * Sinon (myCategorieId = 0), une Categorie et crée.
	 * Le nom de la Categorie est ensuite modifié dans la BD
	 * -------------
	 * Redirection vers CategoriesServlet
	 * -------------
	 * ATTENTION : Le GET détermine le POST par la valeur de myCategorieId
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (Tools.getLoggedInUser(req.getSession()) == null) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/login.jsp");
		}
		
		int produitId;
		try {
			produitId = Integer.parseInt(req.getParameter("produitId"));
		}
		catch(NumberFormatException e) {produitId = -1;}
		
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		Fabricant fabricant;
		if (this.myFabricantId != 0) {
			fabricant = gt.findFabricantById(myFabricantId);
		}
		else {
			fabricant = new Fabricant();
		}
		gt.modifyFabricant(name, address, fabricant);
		
		if (produitId == 0){
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits/edit?produitId=0");
		}
		else if (produitId == -1) {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/fabricants");
		}
		else {
			resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits/edit?produitId="+produitId);
		}	}
	
	
	
	

}
