package com.fabio.parkingapi.entities.enums;

public enum PeriodType {

    FRACIONADO(1,"Fracionado"),
    DIARIA(2,"Diaria"),
    MENSAL(3,"Mensal");


    private int cod;
    private String description;

    private PeriodType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static PeriodType toEnum(Integer id){

        if(id == null) return null;

        for (PeriodType c: PeriodType.values()){
            if (id.equals(c.getCod())) return c;
        }

        throw new IllegalArgumentException("Id invalid "+id);

    }
}
