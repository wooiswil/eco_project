package eco.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eco.dao.EmployeeImp;
import eco.dao.ProduitImp;
import eco.model.Employee;
import eco.model.Produit;

@Controller
public class AdminController {
	
	// Verification des compatibilité entre les columns et les attributs via les classs d'implémentation avec @Autowired
	@Autowired
	EmployeeImp empImp;
	
	@Autowired
	ProduitImp prdImp;
	
	// Variable static pour les dossiers photos (upload)
	private static String empUpload = "src/main/resources/static/empUploads/";
	private static String prdUpload = "src/main/resources/static/prdUploads/";
	
	////////////////
	// Login Section
	////////////////
	
	// pour accéder  à la page connexion des employees | des redirections selon les roles (employee)
	@RequestMapping("/admin")
	public String accessToSignIn(
			// récupération des valeurs de champ de form
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "password", required = false) String password,
			Model m,
			HttpSession s
			) {
		
		
		// verification identifiants rentrés si != msg d'erreur
		// affichage du msgE
		if(s.getAttribute("msgE") != null) {
			m.addAttribute("msg", s.getAttribute("msgE"));
		}
		
		// verification des valeurs 
		if(email != null && password != null) {
			
			// super-admin condition
			if(email.equals("admin2@gmail.com") && password.equals("admin2")) {
				
				// creation des attributs puis en enregistrement avec la session
				m.addAttribute("username", username);
				m.addAttribute("email", email);
				m.addAttribute("password", password);
				s.setAttribute("role", "Super-admin");
				s.setAttribute("username", username);
				s.setAttribute("email", email);
				// redirection vers le homeAdmin "admin/dashboard" si les conditions sont remplies
				System.out.println("Creation d'attributs effectué, identifiants Super-admin reconnus, redirection version /homeAdmin");
				return "redirect:/homeAdmin";
				
				// verif identifiants != de Super-admin dans la bdd
			}else if(empImp.verifByEmail(email) != null) {
				
				// methode de recherche des identifiants dans la dbb
				if(empImp.verifByEmail(email).getPassword().equals(password)) {
					
					
					s.setAttribute("role", empImp.verifByEmail(email).getRole());
					s.setAttribute("nom", empImp.verifByEmail(email).getNom());
					// redirection vers le homeAdmin "admin/dashboard" si les conditions sont remplies
					System.out.println("Creation d'attributs effectué, identifiants employee reconnus, redirection version /homeAdmin");
					return "redirect:/homeAdmin";
				} else {
					System.out.println("Identifiants : " + email + " " + password + " non reconnus");
					m.addAttribute("msg", "Username/Password incorrect");
				}
			} else {
				s.setAttribute("msgE", null);
			}
		}		
		return "admin/signin";
	}
	
	
	
	// accéder au dasboard // une sécurité sera mis en place quand aux rôles autorisé
	@RequestMapping("/homeAdmin")
	public String accessToAdmin (
			HttpSession s,
			Model m
			) {
		
		// en cas de déconnexion, l'user est redirigé vers la page de login sinon
		if(s.getAttribute("role") != null) {
			
			m.addAttribute("email", s.getAttribute("email"));
			m.addAttribute("nom", s.getAttribute("nom"));
			m.addAttribute("role", s.getAttribute("role"));
			m.addAttribute("username", s.getAttribute("username"));
			
			return "admin/dashboard";
		} else {
			
			s.setAttribute("msgE", "Veuillez vous connecter !");
			
			return "redirect:/admin";
		}
		
	}
	
	/////////////////////////////////////////////////////
	// Employee, Client, Commande, Produit liste section
	/////////////////////////////////////////////////////
	
	// listEmp
	@RequestMapping("/getEmp")
	public String getEmpList(
			Model m,
			HttpSession s
			) {
		
		// pour l'affichage des employees
		List<Employee> listEmp = (List<Employee>) empImp.getEmp();
		
		// condition d'affichage liste selon role
		if(s.getAttribute("role")!= "Super-Admin") {
			m.addAttribute("msgE", "Vous n'avez pas les priviligès pour cette requete");
		}
		
		// creation d'attributs pour l'affichage
		m.addAttribute("emp", listEmp);
		m.addAttribute("role", s.getAttribute("role"));
		m.addAttribute("msgE", null);
		
		// Verification du retour de la collection
		System.out.println("Il y a " + listEmp.size() + " employees dans la base de données");
		
		return "redirect:/admin";
	}
	
	// listPrd
	@RequestMapping("/getPrd")
	public String getPrdList(
			Model m,
			HttpSession s
			) {
		// pour l'affichage des produits
		List<Produit> listPrd = (List<Produit>)prdImp.getPrd();
		
		// creation d'attributs pour l'affichage
		m.addAttribute("emp", listPrd);
		m.addAttribute("role", s.getAttribute("role"));
		m.addAttribute("msgE", null);
		
		// Verification du retour de la collection
		System.out.println("Il y a " + listPrd.size() + " employees dans la base de données");
				
		return "redirect:/admin";
	}
	
	
	/////////////////////////////////////////////////////
	// Employee, Client, Commande, Produit update section
	/////////////////////////////////////////////////////
	
	// update emp 
	
	
	/////////////////////////////////////////////////////
	// Employee, Client, Commande, Produit delete section
	/////////////////////////////////////////////////////
	
	// delete emp
}
