package com.fabio.parkingapi.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class NewParkingDto implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private com.fabio.parkingapi.entities.enums.PeriodType periodType;


    public NewParkingDto(){}

    public NewParkingDto(String license, String model, String color, com.fabio.parkingapi.entities.enums.PeriodType periodType) {
        this.license = license;
        this.model = model;
        this.color = color;
        this.periodType = periodType;
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

    public com.fabio.parkingapi.entities.enums.PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(com.fabio.parkingapi.entities.enums.PeriodType periodType) {
        this.periodType = periodType;
    }
}
