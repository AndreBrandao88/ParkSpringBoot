package com.fabio.parkingapi.dtos;

import com.fabio.parkingapi.entities.enums.PeriodType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UpdateParkingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "The ID cannot be null.")
    private Long id;
    @NotNull(message = "The License cannot be null.")
    @Size(min = 7, max = 7, message = "The License informed is invalid.")
    private String license;
    @NotNull(message = "The Model cannot be null.")
    @Size(min = 2, max = 50, message = "The model informed is invalid.")
    private String model;
    @NotNull(message = "The Color cannot be null.")
    @Size(min = 2, max = 30, message = "The Color informed is invalid.")
    private String color;
    @NotNull(message = "The Period Type cannot be null.")
    private PeriodType periodType;

    public UpdateParkingDto(){}

    public UpdateParkingDto(Long id, String license, String model, String color, PeriodType periodType) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.color = color;
        this.periodType = periodType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }
}
