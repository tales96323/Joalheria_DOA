package doa_jewelry.controller;

import doa_jewelry.dto.EmployeeDTO;
import doa_jewelry.entity.Employee;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Criar um novo funcionário
    @PostMapping
    public ResponseEntity<EmployeeDTO> criarEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            EmployeeDTO responseDTO = new EmployeeDTO(savedEmployee);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // Obter funcionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> obterEmployeePorId(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            EmployeeDTO responseDTO = new EmployeeDTO(employee);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listarEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> responseDTOs = employees.stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Atualizar um funcionário existente
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> atualizarEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            EmployeeDTO responseDTO = new EmployeeDTO(updatedEmployee);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Deletar funcionário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmployeePorId(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
