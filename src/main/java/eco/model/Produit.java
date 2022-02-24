package eco.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_prod;
	String ref_prod, nom_prod,prix_unitaireHT, qye_stock, genre, couleur, taille, poids, infos_art;
	
//	// Associations (relations)
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE} ,
//			fetch = FetchType.LAZY)
//		private List<Activite_sportive> act_sport = new ArrayList<Activite_sportive>();
//		private List<Commande> cde = new ArrayList<Commande>();
//		private List<Categorie_produit> cat_prd = new ArrayList<Categorie_produit>();
//		private List<Marque> mrq = new ArrayList<Marque>();
	
	// Accesseurs
	public int getId_prod() {
		return id_prod;
	}
	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}
	public String getRef_prod() {
		return ref_prod;
	}
	public void setRef_prod(String ref_prod) {
		this.ref_prod = ref_prod;
	}
	public String getNom_prod() {
		return nom_prod;
	}
	public void setNom_prod(String nom_prod) {
		this.nom_prod = nom_prod;
	}
	public String getPrix_unitaireHT() {
		return prix_unitaireHT;
	}
	public void setPrix_unitaireHT(String prix_unitaireHT) {
		this.prix_unitaireHT = prix_unitaireHT;
	}
	public String getQye_stock() {
		return qye_stock;
	}
	public void setQye_stock(String qye_stock) {
		this.qye_stock = qye_stock;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getPoids() {
		return poids;
	}
	public void setPoids(String poids) {
		this.poids = poids;
	}
	public String getInfos_art() {
		return infos_art;
	}
	public void setInfos_art(String infos_art) {
		this.infos_art = infos_art;
	}
	
//	public boolean add(Activite_sportive e) {
//		return act_sport.add(e);
//	}
//	public boolean add(Commande e) {
//		return cde.add(e);
//	}
//	public boolean add(Categorie_produit e) {
//		return cat_prd.add(e);
//	}
//	public boolean remove(Object o) {
//		return mrq.remove(o);
//	}
//	public void add(int index, Marque element) {
//		mrq.add(index, element);
//	}
	
	
}
