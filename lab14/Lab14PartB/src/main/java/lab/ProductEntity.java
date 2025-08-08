package lab;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private Integer qty_stock;

    public ProductEntity() {}
    public ProductEntity(String name, Double price, Integer qty_stock) {
        this.name = name;
        this.price = price;
        this.qty_stock = qty_stock;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty_stock() {
        return qty_stock;
    }

    public void setQty_stock(Integer qty_stock) {
        this.qty_stock = qty_stock;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
