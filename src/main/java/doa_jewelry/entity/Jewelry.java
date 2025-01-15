package doa_jewelry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "jewelry")
public class Jewelry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MaterialType material;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private JewelryCategory category;

    // Construtor padrão (obrigatório para JPA)
    public Jewelry() {}

    // Construtor com argumentos
    public Jewelry(String name, MaterialType material, double weight, double price, int stockQuantity, JewelryCategory category) {
        this.name = name;
        this.material = material;
        this.weight = weight;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public JewelryCategory getCategory() {
        return category;
    }

    public void setCategory(JewelryCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Jewelry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", material=" + material +
                ", weight=" + weight +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category=" + category +
                '}';
    }
}
