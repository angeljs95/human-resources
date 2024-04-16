package angel.hr.Controller;

import angel.hr.Entity.Employee;
import angel.hr.Exception.ResourceNotFound;
import angel.hr.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/hr-app
@RequestMapping("hr-app")
@CrossOrigin(value = "http://localhost:3000")

public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    //http://localhost:8080/hr-app/employees
    @GetMapping("/employees")
    public List<Employee> employeeList() {
        List<Employee> employees = employeeService.listEmployees();
        employees.forEach(employee -> logger.info(employee.toString()));
        return employees;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        Employee employeeNew = employeeService.saveEmployee(employee);
        logger.info("Employes has been added: " + employeeNew.toString());
        return ResponseEntity.ok(employeeNew);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            throw new ResourceNotFound("the employee has not been found: " + id);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeUpdate) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            employee.setName(employeeUpdate.getName());
            employee.setDni(employeeUpdate.getDni());
            employee.setDepartment(employeeUpdate.getDepartment());
            employee.setEmail(employeeUpdate.getEmail());
            employee.setSalary(employeeUpdate.getSalary());
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok(employee);
        } else {
            throw new ResourceNotFound("the employee has not been found: " + id);
        }

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.findEmployeeById(id);
        Map<String, Boolean> response = new HashMap<>();
        if (employee != null) {
            employeeService.removeEmploye(employee);
            logger.info("The employee has been deleted: " + employee.getName());
            response.put("Removed", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } else {
            throw new ResourceNotFound("the employee has not been found: " + id);
        }
    }
}
