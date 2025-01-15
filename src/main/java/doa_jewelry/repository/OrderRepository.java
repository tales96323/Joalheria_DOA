package doa_jewelry.repository;

import doa_jewelry.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("SELECT p FROM Order p WHERE p.status = :status")
//    List<Order> findOrdersByStatus(@Param("status") String status);

//    @Query("SELECT p FROM Order p WHERE p.cliente.id = :clienteId")
//    List<Order> findOrdersByCustomerId(@Param("clienteId") Long clienteId);

//    @Query("SELECT p FROM Order p WHERE p.totalAmount > :amount")
//    List<Order> findOrdersWithTotalAmountGreaterThan(@Param("amount") Double amount);

//    @Query("SELECT COUNT(p) FROM Order p WHERE p.status = 'DELIVERED'")
//    Long countDeliveredOrders();

//    @Query("SELECT AVG(p.totalAmount) FROM Order p")
//    Double findAverageOrderValue();
}
