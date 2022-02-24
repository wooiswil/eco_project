package eco.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eco.dao.EmployeeImp;
import eco.model.Employee;

@Controller
@RequestMapping("/Employee") // => permet la protection des urls
public class EmployeeController {

	
	@Autowired
	EmployeeImp empImp;
	
	// Variable static pour les dossiers photos (upload)
	private static String empUpload = "src/main/resources/static/empUploads/";
		
		// affiche la page d'inscription pour les employees
		@RequestMapping(value = "/ajout", method = RequestMethod.GET)
		public String toAddpEmp(
				HttpSession s,
				Model m
				) {
			
			
			return "form/empSign";
		}
		
	// pour accéder  à la page inscritpion des employees | des redirections selon les roles
		@RequestMapping(value = "/empIns", method = RequestMethod.POST)
		public String getEmpForm(
				// récupération des valeurs de champ de form
				@RequestParam(name = "nom", required = false) String nom,
				@RequestParam(name = "email", required = false) String email,
				@RequestParam(name = "role", required = false) String role,
				@RequestParam(name = "username", required = false) String username,
				@RequestParam(name = "photo", required = false) MultipartFile photo,
				@RequestParam(name = "pwd", required = false) String pwd,
				RedirectAttributes rA
				)throws IOException {
			// pour verifier si il y a une piece jointe chargée, on utilise isEmpty()
			if (photo.isEmpty()) {
				// afficher un msg selon les conditions dans une page web en Spring boot ==> ObjectRedirectAttribute.addFlashAttribute("nomAttr", "valeur de msg")
				rA.addFlashAttribute("msg", "Il faut ajouter une photo");
				
				return "redirect:/Employee/ajout";
			} else {
				
				if(empImp.verifByEmail(email) != null) {
					System.out.println("test2");
					rA.addFlashAttribute("msg", "L'email : "+ email + " exist deja");
					rA.addFlashAttribute("nom", nom);
					rA.addFlashAttribute("username", username);
					rA.addFlashAttribute("email", email);
					System.out.println(email);
					return "redirect:/Employee/ajout";
				}
				
				// affichage de nom de pièce jointe (avec getOriginalFileName()
				System.out.println("Photo " + photo.getOriginalFilename());
				
				// stocker la photo dans static/uploads avec l'objet getByte()
				byte [] bytes = photo.getBytes();
				
				// creation d'objet path pour stocker
				Path ph =Paths.get(empUpload+photo.getOriginalFilename());
				
				// pour upload dans ph
				Files.write(ph, bytes);
				
				// affichage du message pour le nom de l'upload
				rA.addFlashAttribute("Mess", "La photo uploadé est : "+ photo.getOriginalFilename());
				
				// Instance de la classe Employee
				Employee emp = new Employee();
				
				// setter les valeurs des champs du form dans les attributs de l'entité (Employee)
				emp.setNom(nom);
				emp.setEmail(email);
				emp.setPassword(pwd);
				emp.setRole(role);
				emp.setUsername(username);
				emp.setPhoto(photo.getOriginalFilename());
				
				// Appelle de fonction d'ajout
				empImp.saveEmp(emp);
				// verification 
				System.out.println("Ajout de l'employee : " +emp.getNom() + " dans la base de données."); 
			}
			// @AdminController
			return "redirect:/getEmp";
		}
}
