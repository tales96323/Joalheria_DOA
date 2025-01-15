package doa_jewelry.service;

import doa_jewelry.entity.Order;
import doa_jewelry.entity.Payment;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.repository.OrderRepository;
import doa_jewelry.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment savePayment(Payment payment) {
        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new EntityNotFoundException(Order.class));

        double totalPaid = paymentRepository.findByOrderId(payment.getOrder().getId())
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();

        if (totalPaid + payment.getAmount() > order.getTotalAmount()) {
            throw new IllegalArgumentException(
                    "Total payment exceeds order value. " +
                            "Order ID: " + order.getId() +
                            ", Total Amount: " + order.getTotalAmount() +
                            ", Total Paid: " + totalPaid +
                            ", Attempted Payment: " + payment.getAmount()
            );
        }

        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        if (!paymentRepository.existsById(payment.getId())) {
            throw new EntityNotFoundException(Payment.class);
        }

        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new EntityNotFoundException(Order.class));

        double totalPaidExcludingCurrent = paymentRepository.findByOrderId(payment.getOrder().getId())
                .stream()
                .filter(existingPayment -> !existingPayment.getId().equals(payment.getId()))
                .mapToDouble(Payment::getAmount)
                .sum();

        if (totalPaidExcludingCurrent + payment.getAmount() > order.getTotalAmount()) {
            throw new IllegalArgumentException(
                    "Total payment exceeds order value. " +
                            "Order ID: " + order.getId() +
                            ", Total Amount: " + order.getTotalAmount() +
                            ", Total Paid Excluding Current: " + totalPaidExcludingCurrent +
                            ", Attempted Payment: " + payment.getAmount()
            );
        }

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Payment.class));
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException(Payment.class);
        }
        paymentRepository.deleteById(id);
    }
}
