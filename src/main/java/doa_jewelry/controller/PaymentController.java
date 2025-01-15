package doa_jewelry.controller;

import doa_jewelry.dto.PaymentDTO;
import doa_jewelry.entity.Payment;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> criarPagamento(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);

        try {
            Payment savedPayment = paymentService.savePayment(payment);
            PaymentDTO responseDTO = new PaymentDTO(savedPayment);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Obter pagamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> obterPagamentoPorId(@PathVariable Long id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            PaymentDTO responseDTO = new PaymentDTO(payment);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Listar todos os pagamentos
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> listarPagamentos() {
        List<Payment> payments = paymentService.getAllPayments();
        List<PaymentDTO> responseDTOs = payments.stream()
                .map(PaymentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Atualizar um pagamento existente
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> atualizarPagamento(@PathVariable Long id, @RequestBody PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);

        try {
            Payment updatedPayment = paymentService.updatePayment(payment);
            PaymentDTO responseDTO = new PaymentDTO(updatedPayment);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Deletar pagamento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamentoPorId(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}