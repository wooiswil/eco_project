package eco.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	
	// req pour afficher la page index du projet (partie front) : localhost:8083/ ==> index.html (return "index";)
	@RequestMapping("/")
	public String getIndex(
			HttpSession s,
			Model m
			) {
		
		// verification des identifiants du client spécifié dans le ClientController
		if(s.getAttribute("clt") != null) {
			if(s.getAttribute("cltId") != null) {
				// récuperation de l'id
				String idCltCo = s.getAttribute("cltId").toString();
				m.addAttribute("idCltCo", idCltCo);
				
				// recupération et enregistrement des identifiants de connexion attribués à client pour la session
				String cltCo = s.getAttribute("clt").toString();
				m.addAttribute("cltCo", cltCo);
				
			}
		}
		
		if(s.getAttribute("msgE") != null) {
			m.addAttribute("msgE", s.getAttribute("msgE"));
		} else {
			m.addAttribute("msgE", null);
		}
		
		return "index";
	}
	
	////////////////
	// logout index
	////////////////
	
	@RequestMapping("/logout")
	public String toLeave(
			HttpSession s
			) {
		
		// affectation de la valeur null à la session de l'user qui permet la deconnexion
		s.setAttribute("clt", null); // clt
		s.setAttribute("msgE", null);
		System.out.println("Deconnexion");
		
		return "redirect:/";
	}
	
}
