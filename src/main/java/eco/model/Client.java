package eco.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

	
	// Attributs ==> permet de définir les critères de table Client qui permettront d'instantier (créer) un objet
	@Id // clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY) // permet l'auto-incrémenation
	int id;
	@Column(unique = true, nullable = false) // permet de rendre cette colone unique et non null
	String login, email, tel;
	String nom_client, prenom_client, password, rue, code_postal, ville, pays;
	
//	// Associations (relations)
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE} ,
//			fetch = FetchType.LAZY)
//		private List<Commande> commande = new ArrayList<Commande>(); 
	
	// Accesseurs ==> permet d'acceder aux données (getNomClient, modifier donnée (setNomClient)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getPrenom_client() {
		return prenom_client;
	}
	public void setPrenom_client(String prenom_client) {
		this.prenom_client = prenom_client;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	// Methodes
//	public boolean add(Commande e) {
//		return commande.add(e);
//	}
//	public Commande remove(int index) {
//		return commande.remove(index);
//	}
	
	
	
}
