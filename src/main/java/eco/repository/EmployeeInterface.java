package eco.repository;

import org.springframework.data.repository.CrudRepository;

import eco.model.Employee;

public interface EmployeeInterface extends CrudRepository<Employee, Integer> {
	
	Employee findByEmail(String email);

}
