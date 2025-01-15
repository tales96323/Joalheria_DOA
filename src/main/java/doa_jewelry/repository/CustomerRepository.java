package doa_jewelry.repository;

import doa_jewelry.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);


    @Query("SELECT c FROM Customer c WHERE c.nif = :nif")
    Optional<Customer> findByNIF(@Param("nif") String nif);

    // Removido c.address.city. O campo address provavelmente é uma String
    @Query("SELECT c FROM Customer c WHERE c.address = :city")
    List<Customer> findCustomersByCity(@Param("city") String city);

    // Contagem de pedidos por cliente (cliente deve estar mapeado corretamente na entidade Order)
    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer = :customer")
    Long countOrdersByCustomer(@Param("customer") Customer customer);

    // Clientes sem pedidos
//    @Query("SELECT c FROM Customer c WHERE c.orders IS EMPTY")
//    List<Customer> findCustomersWithNoOrders();
}
