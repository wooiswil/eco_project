package eco.repository;

import org.springframework.data.repository.CrudRepository;

import eco.model.Commande;

public interface CommandeInterface extends CrudRepository<Commande, Integer> {

}
