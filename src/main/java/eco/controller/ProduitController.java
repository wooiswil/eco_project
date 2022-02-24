package eco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eco.dao.ProduitImp;
import eco.model.Produit;

@Controller
@RequestMapping("/Produit")
public class ProduitController {

	// Verification des compatibilité entre les columns et les attributs via les classs d'implémentation avec @Autowired
	@Autowired
	ProduitImp prdImp;
	
	// Variable static pour les dossiers photos (upload)
	private static String prdUpload = "src/main/resources/static/prdUploads/";
	
	// affiche la page de formulaire pour les produits
	@RequestMapping(value = "/ajoutPrd", method = RequestMethod.GET)
	public String accessPrdForm() {
		
		return "form/addPrd";
	}
	
	// methode d'ajout du produit depuis le formulaire dans la bdd 
	@RequestMapping(value = "/addPrd", method = RequestMethod.POST)
	public String getPrdForm(
			// récupération des valeurs de champ de form
			@RequestParam(name = "ref", required = false) String ref,
			@RequestParam(name = "nomPrd", required = false) String nomPrd,
			@RequestParam(name = "prix", required = false) String prix,
			@RequestParam(name = "genre", required = false) String genre,
			@RequestParam(name = "photo", required = false) MultipartFile photo,
			@RequestParam(name = "qte", required = false) String qte,
			@RequestParam(name = "couleur", required = false) String couleur,
			@RequestParam(name = "poids", required = false) String poids,
			@RequestParam(name = "info", required = false) String info,
			RedirectAttributes rA
			)throws IOException {
		
		// pour verifier si il y a une piece jointe chargée, on utilise isEmpty()
		if (photo.isEmpty()) {
			// afficher un msg selon les conditions dans une page web en Spring boot ==> ObjectRedirectAttribute.addFlashAttribute("nomAttr", "valeur de msg")
			rA.addFlashAttribute("msg", "Il faut ajouter une photo");
			
			return "redirect:/Produit/ajoutPrd";
		} else {
			if(prdImp.searchByNPrd(ref) != null) {
				System.out.println("Ref : " + ref + " déjà dans la base de donnée");
				rA.addFlashAttribute("msg", "Ref : " + ref + " déjà dans la base de donnée");
				rA.addFlashAttribute("nomPrd", nomPrd);
				return "redirect:/Produit/ajoutPrd";
			}
			
			// affichage de nom de pièce jointe (avec getOriginalFileName()
			System.out.println("Photo " + photo.getOriginalFilename());
			
			// stocker la photo dans static/uploads avec l'objet getByte()
			byte [] bytes = photo.getBytes();
			
			// creation d'objet path pour stocker
			Path ph =Paths.get(prdUpload+photo.getOriginalFilename());
			
			// pour upload dans ph
			Files.write(ph, bytes);
			
			// affichage du message pour le nom de l'upload
			rA.addFlashAttribute("Mess", "La photo uploadé est : "+ photo.getOriginalFilename());
		
			// Instance de la classe
			Produit prd = new Produit();
			
			// setter les valeurs des champs du form dans les attributs de l'entité (Produit)
			prd.setRefProd(ref);
			prd.setNomProd(nomPrd);
			prd.setPrixUnitaireHT(Double.parseDouble(prix));
			prd.setGenre(genre);
			prd.setPhotoProd(photo.getOriginalFilename());
			prd.setQteStock(Integer.parseInt(qte));
			prd.setCouleur(couleur);
			prd.setPoids(Double.parseDouble(poids));
			prd.setInfosArt(info);
			
			// Appelle de fonction d'ajout de la class d'implémentation
			prdImp.addPrd(prd);
			
			// verification 
			System.out.println("Le produit : " +prd.getNomProd() + " a été ajouté dans la base de données.");
		}
		
		// @AdminController
		return "redirect:/getPrd";
	}
	
}
