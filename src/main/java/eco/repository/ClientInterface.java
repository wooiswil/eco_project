package eco.repository;

import org.springframework.data.repository.CrudRepository;

import eco.model.Client;

public interface ClientInterface extends CrudRepository<Client, Integer> {
	
	Client findByEmail(String email);

}
