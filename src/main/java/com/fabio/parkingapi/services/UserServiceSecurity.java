package com.fabio.parkingapi.services;

import com.fabio.parkingapi.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceSecurity {

    public static UserSS authenticated(){

        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
