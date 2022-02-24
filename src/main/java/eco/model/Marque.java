package eco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marque {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_marque;
	String nom_marque;
	
	// Accesseurs
	public int getId_marque() {
		return id_marque;
	}
	public void setId_marque(int id_marque) {
		this.id_marque = id_marque;
	}
	public String getNom_marque() {
		return nom_marque;
	}
	public void setNom_marque(String nom_marque) {
		this.nom_marque = nom_marque;
	}
	
	
}
