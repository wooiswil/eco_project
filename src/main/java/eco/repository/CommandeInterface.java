package eco.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eco.model.Commande;

public interface CommandeInterface extends CrudRepository<Commande, Integer> {

	List<Commande> findByIdClient(int idClient);
}
