package angel.hr.Service;

import angel.hr.Entity.Employee;

import java.util.List;

public interface IEmployeeService {

    public Employee findEmployeeById(Integer id);
    public List<Employee> listEmployees();

    public Employee saveEmployee(Employee employee);
    public void removeEmploye(Employee employee);
}
