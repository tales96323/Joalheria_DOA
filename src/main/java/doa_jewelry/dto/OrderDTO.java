package doa_jewelry.dto;

import doa_jewelry.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO representing an Order")
public class OrderDTO {

    @Schema(description = "Order ID", example = "1")
    private Long id;
    @Schema(description = "Customer ID", example = "101")
    private Long customerId;
    @Schema(description = "Order date in YYYY-MM-DD format", example = "2023-01-15")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Schema(description = "Total amount of the order", example = "299.99")
    private Double totalAmount;
    @Schema(description = "List of items in the order")
    private List<String> items;

    public OrderDTO() {}

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomer().getId();
        this.date = order.getDate().toString();
        this.totalAmount = order.getTotalAmount();
        this.items = order.getItems().stream()
                .map(item -> item.hashCode() + " x" + item.getQuantity())
                .toList();

    }

}
