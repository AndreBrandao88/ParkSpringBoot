package com.fabio.parkingapi.dtos;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class NewUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "The name must filled.")
    private String name;
    @NotEmpty(message = "The email must filled.")
    private String email;
    @NotEmpty(message = "The password must filled.")
    private String password;

    public NewUserDto(){}

    public NewUserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
