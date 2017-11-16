package fr.mathieu.commande;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.mathieu.categorie.Categorie;

@Stateless
public class GestionCommande {
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	
	public void addCommande(Commande commande) {
		em.persist(commande);
	}
	
	public List<Commande> getCommandes(){
		TypedQuery<Commande> query = em.createQuery("from " + Commande.class.getSimpleName(), Commande.class);
		return query.getResultList();
	}

	public Commande findCommandeById(Integer commandeId) {
		Commande commande = em.find(Commande.class, commandeId);
		return commande;
	}

}
