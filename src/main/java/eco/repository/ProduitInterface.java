package eco.repository;

import org.springframework.data.repository.CrudRepository;

import eco.model.Produit;

public interface ProduitInterface extends CrudRepository<Produit, Integer> {

}
