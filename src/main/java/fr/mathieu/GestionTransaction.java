package fr.mathieu;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class GestionTransaction {

	@PersistenceContext(name="Catalogue")
	EntityManager em;

	public Produit findProduitById(int id) {
		Produit p = em.find(Produit.class, id);
		return p;
	}
	
	public Categorie ajouterCategorie() {
		Categorie c = new Categorie();
		c.setNom("categorie " + new Date());
		em.persist(c);
		return c;
	}
	
	public void modifierNomCategorie(String nom, Categorie categorie) {
		categorie.setNom(nom);
		em.merge(categorie);
	}
	
	public Fabricant ajouterFabricant() {
		Fabricant f = new Fabricant();
		f.setNom("fabriquant " + new Date());
		em.persist(f);
		return f;
	}
	
	public Produit ajouterProduit(Produit produit) {
		return em.merge(produit);
	}
	
	public List<Categorie> importCategories(){
		TypedQuery<Categorie> query = em.createQuery("from " + Categorie.class.getSimpleName(), Categorie.class);
		return query.getResultList();
	}
	
	public List<Fabricant> importFabricants(){
		TypedQuery<Fabricant> query = em.createQuery("from " + Fabricant.class.getSimpleName(), Fabricant.class);
		return query.getResultList();
	}
	
	public Categorie findByIdCategorie(int id) {
		Categorie categorie = em.find(Categorie.class, id);
		return categorie;
	}
	
	public Categorie removeByIdCategorie(int id) {
		Categorie categorie = this.findByIdCategorie(id);
		
		for (Produit current : categorie.getProduits()) {
			em.remove(current);
		}
		em.remove(categorie);
		return categorie;
	}
	
}
