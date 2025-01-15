package doa_jewelry.service;

import doa_jewelry.entity.Jewelry;
import doa_jewelry.entity.Order;
import doa_jewelry.entity.OrderItem;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.repository.JewelryRepository;
import doa_jewelry.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JewelryRepository jewelryRepository;

    public Order createOrder(Order order) {
        // Verificar o estoque antes de salvar o pedido
        for (OrderItem item : order.getItems()) {
            Jewelry jewelry = jewelryRepository.findById(item.getJewelry().getId())
                    .orElseThrow(() -> new EntityNotFoundException(Jewelry.class));

            if (jewelry.getStockQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for jewelry: " + jewelry.getName() +
                                ". Available: " + jewelry.getStockQuantity() +
                                ", Requested: " + item.getQuantity()
                );
            }
        }

        // Atualizar o estoque das joias
        for (OrderItem item : order.getItems()) {
            Jewelry jewelry = jewelryRepository.findById(item.getJewelry().getId()).get();
            jewelry.setStockQuantity(jewelry.getStockQuantity() - item.getQuantity());
            jewelryRepository.save(jewelry);
        }

        return orderRepository.save(order);
    }

    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order updateOrder(Order order) throws EntityNotFoundException {
        if (!orderRepository.existsById(order.getId())) {
            throw new EntityNotFoundException(Order.class);
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Order.class));

        // Restaurar o estoque das joias associadas
        for (OrderItem item : order.getItems()) {
            Jewelry jewelry = jewelryRepository.findById(item.getJewelry().getId())
                    .orElseThrow(() -> new EntityNotFoundException(Jewelry.class));
            jewelry.setStockQuantity(jewelry.getStockQuantity() + item.getQuantity());
            jewelryRepository.save(jewelry);
        }

        // Deletar a ordem
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Order.class));
    }
}
