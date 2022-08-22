package com.fabio.parkingapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_prices")
public class PriceTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String description;
    @Column(nullable = false)
    private Double price;
    private LocalDateTime creation = LocalDateTime.now();

    public PriceTable(){}

    public PriceTable(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceTable that = (PriceTable) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(creation, that.creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, creation);
    }
}
