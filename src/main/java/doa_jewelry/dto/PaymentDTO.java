package doa_jewelry.dto;

import doa_jewelry.entity.Payment;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing a Payment")
public class PaymentDTO {

    private Long id;
    private Double amount;
    private String date;
    private String method;

    public PaymentDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.date = payment.getDate().toString();
        this.method = payment.getMethod().toString();
    }

}
