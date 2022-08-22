package com.fabio.parkingapi.dtos;

import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String license;
    private String model;
    private String color;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime entrance;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime exit;
    private PeriodType periodType;
    private Double bill;

    public ParkingDto(){}

    public ParkingDto(Long id, String license, String model, String color, LocalDateTime entrance, LocalDateTime exit,
                      PeriodType periodType, Double bill) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.color = color;
        this.entrance = entrance;
        this.exit = exit;
        this.periodType = periodType;
        this.bill = bill;
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

    public LocalDateTime getEntrance() {
        return entrance;
    }

    public void setEntrance(LocalDateTime entrance) {
        this.entrance = entrance;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public void setExit(LocalDateTime exit) {
        this.exit = exit;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }
}
