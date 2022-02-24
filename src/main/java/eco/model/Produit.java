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
	String refProd, nomProd, genre, couleur, infosArt, photoProd;
	Double prixUnitaireHT, poids;
	int qteStock;
	
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
	public String getRefProd() {
		return refProd;
	}
	public void setRefProd(String refProd) {
		this.refProd = refProd;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
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
	public String getInfosArt() {
		return infosArt;
	}
	public void setInfosArt(String infosArt) {
		this.infosArt = infosArt;
	}
	public String getPhotoProd() {
		return photoProd;
	}
	public void setPhotoProd(String photoProd) {
		this.photoProd = photoProd;
	}
	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}
	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public int getQteStock() {
		return qteStock;
	}
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
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
