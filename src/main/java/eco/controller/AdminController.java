package eco.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import eco.dao.EmployeeImp;
import eco.model.Employee;

@Controller
public class AdminController {
	
	// Verification des compatibilité entre les columns et les attributs via les classs d'implémentation avec @Autowired
	@Autowired
	EmployeeImp empImp;
	
	// Variable static pour les dossiers photos (upload)
	
	
	////////////////
	// Login Section
	////////////////
	
	// pour accéder  à la page connexion des employees | des redirections selon les roles (employee)
	@RequestMapping("/admin")
	public String accessToSignIn(
			// récupération des valeurs de champ de form
			@RequestAttribute(name = "username", required = false) String username,
			@RequestAttribute(name = "email", required = false) String email,
			@RequestAttribute(name = "password", required = false) String password,
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
			System.out.println("test1");
			// super-admin condition
			if(email.equals("admin2@gmail.com") && password.equals("admin2")) {
				System.out.println("test2");
				// creation des attributs puis en enregistrement avec la session
				m.addAttribute("username", username);
				m.addAttribute("email", email);
				m.addAttribute("password", password);
				s.setAttribute("role", "Super-admin");
				s.setAttribute("username", username);
				s.setAttribute("email", email);
				System.out.println("test3");
				// redirection vers le homeAdmin "admin/dashboard" si les conditions sont remplies
				return "redirect:/homeAdmin";
				
				// verif identifiants dans la bdd
			}else if(empImp.verifByEmail(email) != null) {
				System.out.println("test4");
				if(empImp.verifByEmail(email).getPassword().equals(password)) {
					
					System.out.println("test5");
					s.setAttribute("role", empImp.verifByEmail(email).getRole());
					s.setAttribute("nom", empImp.verifByEmail(email).getNom());
					// redirection vers le homeAdmin "admin/dashboard" si les conditions sont remplies
					System.out.println("test6");
					return "redirect:/homeAdmin";
				} else {
					System.out.println("test7");
					m.addAttribute("msg", "Username/Password incorrect");
				}
			} else {
				System.out.println("test8");
				s.setAttribute("msgE", null);
			}
			
			// methode de recherche des identifiants dans la dbb
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
			System.out.println("test A");
			m.addAttribute("email", s.getAttribute("email"));
			m.addAttribute("nom", s.getAttribute("nom"));
			m.addAttribute("role", s.getAttribute("role"));
			m.addAttribute("username", s.getAttribute("username"));
			System.out.println("test B");
			return "admin/dashboard";
		} else {
			System.out.println("test C");
			s.setAttribute("msgE", "Veuillez vous connecter !");
			System.out.println("test D");
			return "redirect:/admin";
		}
		
	}
	
	////////////////////
	// Employee section
	////////////////////
	
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
			m.addAttribute("msgEmp", "Vous n'avez pas les priviligès pour cette requete");
		}
		
		// creation d'attributs pour l'affichage
		m.addAttribute("emp", listEmp);
		m.addAttribute("empR", s.getAttribute("role"));
		m.addAttribute("msgEmp", null);
		
		// Verification du retour de la collection
		System.out.println("Il y a " + listEmp.size() + " employees dans la base de données");
		
		return "redirect:/admin";
	}
}
