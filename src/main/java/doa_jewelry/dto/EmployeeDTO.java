package doa_jewelry.dto;

import doa_jewelry.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing an Employee")
public class EmployeeDTO {

    private Long id;
    private String name;
    private String nif;
    private String hireDate;
    private Double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.nif = employee.getNif();
        this.hireDate = employee.getHireDate().toString();
        this.salary = employee.getSalary();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}
