package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.EmailDto;
import com.fabio.parkingapi.entities.EmailModel;
import com.fabio.parkingapi.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/${api.version}/sendEmails")
@Api(tags = "Email Controller")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @ApiOperation("Send email.")
    public ResponseEntity<EmailDto> sendEmail(@RequestBody @Valid EmailDto emailDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.sendEmail(emailDto));
    }
}
