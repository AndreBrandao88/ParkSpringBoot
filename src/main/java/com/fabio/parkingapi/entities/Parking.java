package com.fabio.parkingapi.entities;

import com.fabio.parkingapi.entities.enums.PeriodType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_parkings")
public class Parking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 8)
    private String license;
    @Column(nullable = false, length = 50)
    private String model;
    @Column(nullable = false, length = 30)
    private String color;
    private LocalDateTime entrance = LocalDateTime.now();
    @Column(length = 30)
    private LocalDateTime exit = null;
    @Column(nullable = false, length = 20)
    private PeriodType periodType;
    private Double bill = null;

    public Parking(Long id, String license, String model, String color, PeriodType periodType) {
        this.id = id;
        this.license = license;
        this.model = model;
        this.color = color;
        this.periodType = periodType;
    }

    public Parking(){}

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
