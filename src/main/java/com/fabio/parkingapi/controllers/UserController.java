package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.NewUserDto;
import com.fabio.parkingapi.entities.UserModel;
import com.fabio.parkingapi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/users")
@Api(tags = "User Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Create a user.")
    public ResponseEntity<UserModel> save(@RequestBody @Valid NewUserDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(dto));
    }

    @GetMapping
    @ApiOperation(value = "List all users.")
    public ResponseEntity<List<UserModel>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a user by ID.")
    public ResponseEntity<UserModel> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation("Update a user by ID.")
    public ResponseEntity<UserModel> updateById(@PathVariable Long id, @RequestBody @Valid UserModel userModel){
        return ResponseEntity.ok(userService.updateById(id,userModel));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation("Delete a user by ID.")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
