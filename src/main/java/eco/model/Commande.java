package eco.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commande {

	// Attriuts
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	int num_bon_commande;
	Date date_commande, date_reglement;
	String moyen_paiement, etat_cde, status_cde;
	Double total_commande;
	Boolean paiement_valide;
	
	
	// Accesseurs
	public int getNum_bon_commande() {
		return num_bon_commande;
	}
	public void setNum_bon_commande(int num_bon_commande) {
		this.num_bon_commande = num_bon_commande;
	}
	public Date getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(Date date_commande) {
		this.date_commande = date_commande;
	}
	public Date getDate_reglement() {
		return date_reglement;
	}
	public void setDate_reglement(Date date_reglement) {
		this.date_reglement = date_reglement;
	}
	public String getMoyen_paiement() {
		return moyen_paiement;
	}
	public void setMoyen_paiement(String moyen_paiement) {
		this.moyen_paiement = moyen_paiement;
	}
	public String getEtat_cde() {
		return etat_cde;
	}
	public void setEtat_cde(String etat_cde) {
		this.etat_cde = etat_cde;
	}
	public String getStatus_cde() {
		return status_cde;
	}
	public void setStatus_cde(String status_cde) {
		this.status_cde = status_cde;
	}
	public Double getTotal_commande() {
		return total_commande;
	}
	public void setTotal_commande(Double total_commande) {
		this.total_commande = total_commande;
	}
	public Boolean getPaiement_valide() {
		return paiement_valide;
	}
	public void setPaiement_valide(Boolean paiement_valide) {
		this.paiement_valide = paiement_valide;
	}
	
	
}
