package com.fabio.parkingapi.entities.enums;

public enum StatusEmail {

    PROCESSING(1,"Processando"),
    SENT(2,"Enviado"),
    ERROR(3,"Error");


    private int cod;
    private String description;

    private StatusEmail(int cod, String description) {
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


    public static StatusEmail toEnum(Integer id){

        if(id == null) return null;

        for (StatusEmail s: StatusEmail.values()){
            if (id.equals(s.getCod())) return s;
        }

        throw new IllegalArgumentException("Id invalid "+id);

    }
}
