package fr.mathieu;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.mathieu.categorie.Categorie;
import fr.mathieu.fabricant.Fabricant;
import fr.mathieu.produit.Produit;

/* Classe permettant la gestion de la base de données à travers plusieurs méthodes réalisants des transactions */
@Stateless
public class GestionTransaction {

	@PersistenceContext(name="Catalogue")
	EntityManager em;
	
	public Produit addProduit(Produit produit) {
		return em.merge(produit);
	}
	
	public Categorie addCategorie(Categorie categorie) {
		em.persist(categorie);
		return categorie;
	}
	
	public Fabricant addFabricant(Fabricant fabricant) {
		em.persist(fabricant);
		return fabricant;
	}
	
	public void modifyCategorieName(String name, Categorie categorie) {
		categorie.setNom(name);
		em.merge(categorie);
	}
	
	public void modifyFabricant(String name, String address, Fabricant fabricant) {
		fabricant.setNom(name);
		fabricant.setAdresse(address);
		em.merge(fabricant);	
	}
	
	public void modifyProduit(Produit produit, String name, String ref, Categorie categorie, Fabricant fabricant) {
		produit.setNom(name);
		produit.setReference(ref);
		produit.setCategorie(categorie);
		produit.setFabricant(fabricant);
		em.merge(produit);	
	}
	
	public List<Categorie> importCategories(){
		TypedQuery<Categorie> query = em.createQuery("from " + Categorie.class.getSimpleName(), Categorie.class);
		return query.getResultList();
	}
	
	public List<Fabricant> importFabricants(){
		TypedQuery<Fabricant> query = em.createQuery("from " + Fabricant.class.getSimpleName(), Fabricant.class);
		return query.getResultList();
	}
	
	public List<Produit> importProduits() {
		TypedQuery<Produit> query = em.createQuery("from " + Produit.class.getSimpleName(), Produit.class);
		return query.getResultList();
	}
	
	public Produit findProduitById(int id) {
		Produit p = em.find(Produit.class, id);
		return p;
	}
	
	public Categorie findCategorieById(int id) {
		Categorie categorie = em.find(Categorie.class, id);
		return categorie;
	}
	
	public Fabricant findFabricantById(int id) {
		Fabricant fabricant = em.find(Fabricant.class, id);
		return fabricant;		
	}
	
	public Categorie removeByIdCategorie(int id) {
		Categorie categorie = this.findCategorieById(id);
		for (Produit current : categorie.getProduits()) {
			em.remove(current);
		}
		em.remove(categorie);
		return categorie;
	}

	public Fabricant removeByIdFabricant(int id) {
		Fabricant fabricant = this.findFabricantById(id);
		for (Produit current : fabricant.getProduits()) {
			em.remove(current);
		}
		em.remove(fabricant);
		return fabricant;
	}

	public Produit removeByIdProduit(int id) {
		Produit produit = this.findProduitById(id);
		em.remove(produit);
		return produit;
	}

	
}
