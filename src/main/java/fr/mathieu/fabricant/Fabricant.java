package fr.mathieu.fabricant;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import fr.mathieu.produit.Produit;

@Entity
public class Fabricant {
	
	// Id généré et incrémenté automatiquement lors l'objet est ajouté à la BD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// Champ obligatoire
	@NotNull
	private String nom;
	
	private String adresse;
	
	// Liste des produits qui ont le Fabricant instancié comme valeur d'attribut
	// Relie produits et le Fabricant dans BD à travers l'annotation @OneToMany (1 Fabricant pour plusieurs Produit)
	@OneToMany(mappedBy="fabricant", fetch = FetchType.EAGER)//fetch : a permis d'utiliser les produits attribués à une Categorie envoyé dans la vue
	private List<Produit> produits;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

}
