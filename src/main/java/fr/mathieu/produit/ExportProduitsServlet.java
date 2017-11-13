package fr.mathieu.produit;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fr.mathieu.GestionTransaction;
import fr.mathieu.categorie.Categorie;
import fr.mathieu.fabricant.Fabricant;

@WebServlet("/produits/export")
@SuppressWarnings("serial")
public class ExportProduitsServlet extends HttpServlet {
	
	@EJB
	GestionTransaction gt;
	
	static List<Method> findGetters(Class<?> c) {
		   List<Method> list = new ArrayList<>();
		   Method[] methods = c.getDeclaredMethods();
		   for (Method method : methods)
		      if (isGetter(method))
		         list.add(method);
		   return list;
		}
	
	public static boolean isGetter(Method method) {
		   if (Modifier.isPublic(method.getModifiers()) &&
		      method.getParameterTypes().length == 0) {
		         if (method.getName().matches("^get[A-Z].*") &&
		            !method.getReturnType().equals(void.class))
		               return true;
		         if (method.getName().matches("^is[A-Z].*") &&
		            method.getReturnType().equals(boolean.class))
		               return true;
		   }
		   return false;
		}
	
	public static boolean isSetter(Method method) {
		   return Modifier.isPublic(method.getModifiers()) &&
		      method.getReturnType().equals(void.class) &&
		         method.getParameterTypes().length == 1 &&
		            method.getName().matches("^set[A-Z].*");
		}

	@SuppressWarnings({ "unused", "rawtypes", "static-access" })
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//semble être nécessaire pour télécherger fichier plutot que de l'afficher dans navigateur
		//marche très bien sans sur Firefox et Chrome
		resp.setHeader("Content-disposition","attachment; filename=produits.xls");
		
		OutputStream os = resp.getOutputStream();
		// create a new workbook
		@SuppressWarnings("resource")
		Workbook wb = new HSSFWorkbook();
		// create a new sheet
		Sheet s = wb.createSheet();
		// declare a row object reference
		Row r = null;
		// declare a cell object reference
		Cell c = null;
		
		//Methode générique pour récupérer attribut des produits: !!! pas vraiment générique pour getCategorie et getFabricant !!!
		Produit produitTest = new Produit();
		Class classProduit = produitTest.getClass();
		List<Method> methodsProduit =  this.findGetters(classProduit);
		
		Categorie categorieTest = new Categorie();
		Class classCategorie = categorieTest.getClass();
		List<Method> methodsCategorie =  this.findGetters(classCategorie);
		
		Fabricant fabricantTest = new Fabricant();
		Class classFabricant = fabricantTest.getClass();
		List<Method> methodsFabricant =  this.findGetters(classFabricant);
				
		List<Produit> produits = gt.importProduits();
		for (int i = 0; i < produits.size(); i++) {
			r = s.createRow(i);
			
			for (int j = 0; j < methodsProduit.size(); j++) {
				c = r.createCell(j);	
				String cellContent = "";
				try {
					
					//exporte le nom de la catégorie, pas l'objet en lui-même
					if (methodsProduit.get(j).invoke(produits.get(i)).getClass() == Categorie.class) {
						cellContent = methodsCategorie.get(1).invoke(methodsProduit.get(j).invoke(produits.get(i))).toString();
					}
					//exporte le nom du fabricant, pas l'objet en lui-même
					else if (methodsProduit.get(j).invoke(produits.get(i)).getClass() == Fabricant.class) {
						cellContent = methodsFabricant.get(2).invoke(methodsProduit.get(j).invoke(produits.get(i))).toString();
					}
					else {
						cellContent = methodsProduit.get(j).invoke(produits.get(i)).toString();
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				c.setCellValue(cellContent);
			}
		}

		wb.write(os);
		os.close();
	
	}

}
