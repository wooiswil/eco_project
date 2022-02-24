package eco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eco.model.Commande;
import eco.repository.CommandeInterface;

@Service
@Transactional
public class CommandeImp {

	
	@Autowired
	CommandeInterface cdeInterface;
	
	// insert into commande
	public void addToCart(Commande cde) {
		
		cdeInterface.save(cde);
	}
	
	// select * from commmande
	public List<Commande> getCart(){
		
		List<Commande> cartList = (List<Commande>)cdeInterface.findAll();
		
		return cartList;
	}
	
	// update
	public void modCart(Commande cde) {
		
		cdeInterface.save(cde);
	}
	
	// delete
	public void rmCart(int idCart) {
		
		cdeInterface.deleteById(idCart);
	}
	
	// search by id
	public Commande searchByid(int idCde) {
		
		// retourne l'enregistrement avec get()
		Commande listById = cdeInterface.findById(idCde).get();
		
		return listById;
	}
	
	public List<Commande> searchbyidClt(int idClt) {
		
		List<Commande> listByIdClt = cdeInterface.findByIdClient(idClt);
		
		return listByIdClt;
	}
}
