package doa_jewelry.service;

import doa_jewelry.entity.Employee;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) throws EntityAlreadyExistsException {
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            throw new EntityAlreadyExistsException(Employee.class);
        }
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateEmployee(Employee funcionario) throws EntityNotFoundException {
        if (!employeeRepository.existsById(funcionario.getId())) {
            throw new EntityNotFoundException(Employee.class);
        }
        return employeeRepository.save(funcionario);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
