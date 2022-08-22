package com.fabio.parkingapi.security;

import java.io.Serializable;

public class Credencial implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String senha;

    public Credencial() {
    }

    public Credencial(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
