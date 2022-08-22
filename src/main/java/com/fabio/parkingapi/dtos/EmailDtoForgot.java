package com.fabio.parkingapi.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDtoForgot implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Email Obrigatorio")
    @Email(message = "Email Invalido")
    private String email;

    public EmailDtoForgot(){}

    public EmailDtoForgot(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
