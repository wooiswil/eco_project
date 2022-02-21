package eco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class DefaultController {

	
	// req pour afficher la page index du projet (partie front) : localhost:8083/ ==> index.html (return "index";)
	@RequestMapping("/")
	public String getIndex() {
		
		return "index";
	}
	
}
