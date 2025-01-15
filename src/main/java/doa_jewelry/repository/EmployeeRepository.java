package doa_jewelry.repository;

import doa_jewelry.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    Optional<Employee> findByName(Employee
//    @Query("SELECT e FROM Employee e WHERE e.nif = :nif")
//    Optional<Employee> findByNIF(@Param("nif") String nif);

//    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
//    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);

//    @Query("SELECT e FROM Employee e WHERE TYPE(e) = 'Gerente'")
//    List<Employee> findAllManagers();

//    @Query("SELECT e FROM Employee e WHERE TYPE(e) = 'Vendedor'")
//    List<Employee> findAllSalespeople();
}
