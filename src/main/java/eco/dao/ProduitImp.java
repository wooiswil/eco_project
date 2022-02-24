package eco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eco.model.Produit;
import eco.repository.ProduitInterface;

@Service
@Transactional
public class ProduitImp {

	@Autowired
	ProduitInterface prdInterface;
	
	
	// insert into produit
	public void addPrd(Produit prd) {
		
		prdInterface.save(prd);
	}
	
	// select * from produit
	public List<Produit> getPrd(){
		
		List<Produit> listprd = (List<Produit>) prdInterface.findAll();
		
		return listprd;
	}
	
	// update produit set *
	public void modPrd(Produit prd) {
		
		prdInterface.save(prd);
	}
	
	// delete from produit
	public void rmPrd(int idPrd) {
		
		prdInterface.deleteById(idPrd);
	}
	
	// search by id
	public Produit searchById(int idPrd){
		
		Produit listPrdBy = prdInterface.findById(idPrd).get();
		
		return listPrdBy;
	}
	
	// search by nom_prd
	public Produit searchByNPrd(String refPrd){
		
		Produit listPrdByNPrd = prdInterface.findByRefProd(refPrd);
		
		return listPrdByNPrd;
	}
}
