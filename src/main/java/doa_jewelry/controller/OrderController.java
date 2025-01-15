package doa_jewelry.controller;

import doa_jewelry.dto.OrderDTO;
import doa_jewelry.entity.Order;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.service.OrderService;
import doa_jewelry.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    // Criar um novo pedido
    @PostMapping
    public ResponseEntity<OrderDTO> criarOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);

        try {
            Order savedOrder = orderService.createOrder(order);
            OrderDTO responseDTO = new OrderDTO(savedOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Obter pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> obterOrderPorId(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            OrderDTO responseDTO = new OrderDTO(order);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<OrderDTO>> listarOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderDTO> responseDTOs = orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Atualizar um pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> atualizarOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);

        try {
            Order updatedOrder = orderService.updateOrder(order);
            OrderDTO responseDTO = new OrderDTO(updatedOrder);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Deletar pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrderPorId(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
