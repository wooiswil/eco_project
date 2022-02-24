package eco.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eco.dao.ClientImp;
import eco.model.Client;

@Controller
public class ClientController {
	
	@Autowired
	ClientImp cltImp;

	
	//////////////////////////
	// Inscription&Connection
	/////////////////////////
	
	@RequestMapping("/getFormClient")
	public String toGetFormUser(
			
		// recuperation des champs de formulaire 
		@RequestParam(name="nom", required = false)String nom,
		@RequestParam(name="prenom", required = false) String prenom,
		@RequestParam(name="email", required = false) String email, 
		@RequestParam(name="login", required = false) String login, 
		@RequestParam(name="mdp", required = false) String mdp, 
		@RequestParam(name="rue", required = false) String rue,
		@RequestParam(name="codeP", required = false) String codeP,
		@RequestParam(name="ville", required = false) String ville,
		@RequestParam(name="pays", required = false) String pays,
		@RequestParam(name="tel", required = false) String tel, 
		@RequestParam(name="btnForm", required = false) String btn,
		Model m, 
		HttpSession s
			) {
		
		// Instance de la classe Client
		Client clt = new Client();
		
		// verification de la valeur du btn
		if(btn != null) {
			System.out.println("btn non null ==> inscription");

			// traitement et instructions pour l'affichage
			// inscription
			if(btn.equals("btnIns")) {
				
				System.out.println("test2");
				// setter les valeurs des champs du form dans les attributs de l'entité (Client)
				clt.setNom_client(nom);
				clt.setPrenom_client(prenom);
				clt.setEmail(email);
				clt.setLogin(login);
				clt.setPassword(mdp);
				clt.setRue(rue);
				clt.setCode_postal(codeP);
				clt.setVille(ville);
				clt.setPays(pays);
				clt.setTel(tel);
				
				
				// Appelle de la methode d'implémentation ==> ici l'ajout 
				cltImp.addClt(clt);
				System.out.println("test3");
				
				// recuperation des informations pour les stockés dans la s
				s.setAttribute("clt", clt.getNom_client()+ " " +clt.getPrenom_client());
				m.addAttribute("cltCo", clt.getNom_client() + " " + clt.getPrenom_client());
				s.setAttribute("cltCo", clt.getNom_client() + " " + clt.getPrenom_client());
				
				// verification
				System.out.println("Bienvenu " + clt.getNom_client());
				System.out.println("test4");
				
				// redirection qui correspond à l'url de l'index.html
				return "redirect:/";
				
			} else if (btn.equals("btnCo")) {
				
				System.out.println("btn non null ==> connexion");
				// creation d'attributs pour l'user connecté
				m.addAttribute("email", email);
				m.addAttribute("mdp", mdp);
				
				// recherche de l'email rentré dans la base de données
				Client verifBdd = cltImp.authCltbyEmail(email);
				
				// verif si email dans la bdd
				if(verifBdd == null) {
					
					// instruction si email non dans la bdd
					// affichage d'un message d'erreur en cas d'identifiants invalides
					s.setAttribute("msgE", "Email " + email + " incorrect");
					return "redirect:/";
					
					// instruction si email dans la bdd ==> verif du mdp
				} else {
					
//					s.setAttribute("MailExist", email);
					// verif email de form == email bdd
					if(verifBdd.getPassword().equals(mdp)) {
						
						// récupération des informations de connect
						s.setAttribute("clt", verifBdd.getNom_client() + " " + verifBdd.getPrenom_client());
						s.setAttribute("cltId", verifBdd.getId());
						
						s.setAttribute("msgE", null);
						return "redirect:/";
					} else {
						
						// instruction si mdp non dans la bdd
						s.setAttribute("msgE", "Password incorrect");
						System.out.println("Mdp incorrect");
						return "redirect:/";
					}
				}
				
			}
		}
		
		return "index";
	}
}
