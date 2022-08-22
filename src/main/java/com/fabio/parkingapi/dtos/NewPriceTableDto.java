package com.fabio.parkingapi.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewPriceTableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The Description cannot be null.")
    @Size(min = 2, max = 50, message = "The Description informed is invalid.")
    private String description;
    @NotNull(message = "Thre Price cannot be null.")
    private Double price;

    public NewPriceTableDto(){}

    public NewPriceTableDto(String description, Double price) {
        this.description = description;
        this.price = price;
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
