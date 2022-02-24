package eco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activite_sportive {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_activite;
	String nom_activite;
	
	
	// Accesseurs
	public int getId_activite() {
		return id_activite;
	}
	public void setId_activite(int id_activite) {
		this.id_activite = id_activite;
	}
	public String getNom_activite() {
		return nom_activite;
	}
	public void setNom_activite(String nom_activite) {
		this.nom_activite = nom_activite;
	}
	
}
