package eco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eco.model.Client;
import eco.repository.ClientInterface;

@Service
@Transactional
public class ClientImp {

	@Autowired
	ClientInterface cltInterface;
	
	
	// insert into
	public void addClt(Client clt) {
		
		cltInterface.save(clt);
	}
	
	// select * from Client
	public List<Client> getClt(){
		
		List<Client> listClt = (List<Client>)cltInterface.findAll();
		
		return listClt;
	}
	
	// delete by id
	public void rmClt(int idClt) {
		
		cltInterface.deleteById(idClt);
	}
	
	// search by id
	public Client searchCltById(int idClt) {
		
		// get() retourne l'enregistrement
		Client searchById = cltInterface.findById(idClt).get();
		
		return searchById;
	}
	
	// search By Email
	public Client authCltbyEmail(String email) {
		
		Client resAuth =  cltInterface.findByEmail(email);
		
		return resAuth;
	}
}
