package eco.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eco.model.Employee;
import eco.repository.EmployeeInterface;

//session_save()
@Service
//pour faire le commit() de la requete (équivalent .hibernatecfg.xml) 
@Transactional
public class EmployeeImp {

	// verifie la compatibilité entre le type des columns et des attributs
	@Autowired
	// Creation d'objet de type UserInterface pour faire appelle aux methode de l'interface
	EmployeeInterface empInterface;
	
	// insert into
	public void saveEmp(Employee emp) {
		
		empInterface.save(emp);
	}
	
	// collection
	public List<Employee> getEmp(){
		
		List<Employee> listEmp = (List<Employee>) empInterface.findAll();
		
		return listEmp;
	}
	
	// update 
	public void modEmp(Employee emp) {
		empInterface.save(emp);
	}
	
	// delete
	public void rmEmp(int idEmp) {
		empInterface.deleteById(idEmp);
	}
	
	// searchById
	public Employee searchEmp(int idEmp) {
		
		// get() retourne l'enregistrement
		Employee searchById = empInterface.findById(idEmp).get();
		
		return searchById;
	}
	
	// searchByEmail
	public Employee verifByEmail(String email) {
		
		Employee searchByEmail = empInterface.findByEmail(email);
		
		return searchByEmail;
	}
}
