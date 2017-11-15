package fr.mathieu;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.mathieu.categorie.Categorie;
import fr.mathieu.fabricant.Fabricant;
import fr.mathieu.produit.Produit;

@RunWith(Arquillian.class)
public class GestionTransactionTest {
	
	@Deployment
	public static Archive<?> createDeployment()
	{
		// TODO Ajouter la data source pour les tests et l'utiliser à la place de ExempleDS dans le fichier persistence.xml
		return ShrinkWrap.create( WebArchive.class, "test.war" )
				.addPackage( Produit.class.getPackage() )
				.addPackage( Categorie.class.getPackage() )
				.addPackage( Fabricant.class.getPackage() )
				.addPackage(GestionTransaction.class.getPackage())
				.addAsLibraries(Maven.resolver().resolve("org.apache.poi:poi:3.17"). withTransitivity().as(JavaArchive.class))
				.addAsResource( "test-persistence.xml", "META-INF/persistence.xml" )
				.addAsWebInfResource( EmptyAsset.INSTANCE, "beans.xml" );
	}
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	GestionTransaction gt;

	
	@Test
	public void testAddCategorie(){
		
		List<Categorie> categories = gt.importCategories();
		int nbrCategoriesBeforeAdd = categories.size();
		
		Categorie categorie = new Categorie();
		categorie.setNom("testAdd");

		
		gt.addCategorie(categorie);
		
		categories = gt.importCategories();
		int nbrCategoriesAfterAdd = categories.size();
		
		assertNotEquals(nbrCategoriesBeforeAdd, nbrCategoriesAfterAdd);
	}
	
	
	@Test
	public void testFindCategorieById(){
		
		Categorie categorie = new Categorie();
		categorie.setNom("testId");
		gt.addCategorie(categorie);
		
		Integer id = categorie.getId();
		
		Categorie categorieId = gt.findCategorieById(id);
		
		assertSame(id, categorieId.getId());
		
	}
	
	@Test
	public void testFindCategorieByName(){
		
		Categorie categorie = new Categorie();
		String searchedName = "testSearched";
		categorie.setNom(searchedName);
		gt.addCategorie(categorie);
		
		List<Categorie> categories = gt.findCategoriesByName(searchedName);
		
		assertSame(searchedName, categories.get(0).getNom());
		
	}
	

}
