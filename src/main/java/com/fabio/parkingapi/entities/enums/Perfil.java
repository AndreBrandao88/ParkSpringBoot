package com.fabio.parkingapi.entities.enums;

public enum Perfil {

    ADMIN(1,"ROLE_ADMIN"),
    USER(2,"ROLE_USER");

    private int cod;
    private String description;

    private Perfil(int cod, String description) {
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


    public static Perfil toEnum(Integer id){

        if(id == null) return null;

        for (Perfil c: Perfil.values()){
            if (id.equals(c.getCod())) return c;
        }

        throw new IllegalArgumentException("Id invalid "+id);

    }

}
