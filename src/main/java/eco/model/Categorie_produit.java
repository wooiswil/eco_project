package eco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie_produit {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_cat_prod;
	String nom_categorie;
	
	
	// Accesseurs
	public int getId_cat_prod() {
		return id_cat_prod;
	}
	public void setId_cat_prod(int id_cat_prod) {
		this.id_cat_prod = id_cat_prod;
	}
	public String getNom_categorie() {
		return nom_categorie;
	}
	public void setNom_categorie(String nom_categorie) {
		this.nom_categorie = nom_categorie;
	}
	
}
