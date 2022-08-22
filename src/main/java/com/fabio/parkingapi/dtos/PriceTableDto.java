package com.fabio.parkingapi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PriceTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Double price;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creation;

    public PriceTableDto(){}

    public PriceTableDto(Long id, String description, Double price, LocalDateTime creation) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.creation = creation;
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
}
