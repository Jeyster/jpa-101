package fr.mathieu.commande;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.mathieu.categorie.Categorie;
import fr.mathieu.produit.Produit;

@Stateless
public class GestionCommande {
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	
	public void addCommande(Commande commande) {
		em.merge(commande);
	}
	
	public List<Commande> getCommandes(){
		TypedQuery<Commande> query = em.createQuery("from " + Commande.class.getSimpleName(), Commande.class);
		return query.getResultList();
	}

	public Commande findCommandeById(Integer commandeId) {
		Commande commande = em.find(Commande.class, commandeId);
		return commande;
	}

	public Commande removeByIdCommande(int commandeId) {
		Commande commande = this.findCommandeById(commandeId);
		em.remove(commande);
		return commande;		
	}

}
