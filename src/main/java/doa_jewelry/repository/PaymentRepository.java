package doa_jewelry.repository;

import doa_jewelry.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByOrderId(Long orderId);

//    @Query("SELECT p FROM Payment p WHERE p.method = :method")
//    List<Payment> findPaymentsByMethod(@Param("method") String method);

//    @Query("SELECT p FROM Payment p WHERE p.amount > :amount")
//    List<Payment> findPaymentsAboveAmount(@Param("amount") Double amount);

//    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.order.id = :orderId")
//    Double calculateTotalPaymentsForOrder(@Param("orderId") Long orderId);
}