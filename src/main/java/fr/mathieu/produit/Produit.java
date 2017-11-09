package fr.mathieu.produit;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import fr.mathieu.categorie.Categorie;
import fr.mathieu.fabricant.Fabricant;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// Relie Produit à Categorie dans BD à travers l'annotation @ManyToOne
	// (plusieurs Produit pour 1 Categorie)
	// cascade merge et persist lorsque Produit ajouté à BD
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private Categorie categorie;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private Fabricant fabricant;
	
	@NotNull
	private String nom;
	
	private String reference;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Fabricant getFabricant() {
		return fabricant;
	}

	public void setFabricant(Fabricant fabricant) {
		this.fabricant = fabricant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


}
