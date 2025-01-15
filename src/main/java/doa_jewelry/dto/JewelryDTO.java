package doa_jewelry.dto;

import doa_jewelry.entity.Jewelry;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing a Jewelry")
public class JewelryDTO {

    private Long id;
    private String name;
    private String type;
    private String material;
    private Double weight;
    private Double price;
    private Integer stockQuantity;

    public JewelryDTO() {}

    public JewelryDTO(Jewelry jewelry) {
        this.id = jewelry.getId();
        this.name = jewelry.getName();
        this.type = jewelry.getCategory().toString();
        this.material = jewelry.getMaterial().toString();
        this.weight = jewelry.getWeight();
        this.price = jewelry.getPrice();
        this.stockQuantity = jewelry.getStockQuantity();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
}
