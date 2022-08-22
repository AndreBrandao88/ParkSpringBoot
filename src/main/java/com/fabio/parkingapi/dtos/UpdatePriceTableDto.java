package com.fabio.parkingapi.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UpdatePriceTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The ID cannot be null.")
    private Long id;
    @NotNull(message = "The Description cannot be null.")
    @Size(min = 2, max = 50, message = "The Description informed is invalid.")
    private String description;
    @NotNull(message = "Thre Price cannot be null.")
    private Double price;

    public UpdatePriceTableDto(){}

    public UpdatePriceTableDto(Long id, String description, Double price) {
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
}
