package angel.hr.Service;

import angel.hr.Entity.Employee;
import angel.hr.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee findEmployeeById(Integer id) {
        Employee employee= employeeRepository.findById(id).orElse(null);

        return employee;
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void removeEmploye(Employee employee) {
        employeeRepository.delete(employee);
    }
}
