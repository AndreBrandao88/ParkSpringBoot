package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.EmailDtoForgot;
import com.fabio.parkingapi.security.JWTUtil;
import com.fabio.parkingapi.security.UserSS;
import com.fabio.parkingapi.services.AuthService;
import com.fabio.parkingapi.services.UserServiceSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/${api.version}/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS user = UserServiceSecurity.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDtoForgot emailDto){
        authService.sendNewPassword(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }

}
