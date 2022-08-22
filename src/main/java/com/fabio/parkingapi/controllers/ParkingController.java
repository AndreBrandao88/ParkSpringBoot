package com.fabio.parkingapi.controllers;


import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.services.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/parkings")
@Api(tags = "Parking Controller")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping
    @ApiOperation(value = "Find all vehicles parked.")
    public ResponseEntity<List<ParkingDto>> findALl(){
        return ResponseEntity.ok(parkingService.findAll());
    }

    @PostMapping
    @ApiOperation(value = "Create a parking and return ParkingDto.")
    public ResponseEntity<ParkingDto> saveParking(@RequestBody @Valid NewParkingDto newParkingDto){
        ParkingDto parkingDto = parkingService.saveParking(newParkingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a parking by ID and return a ParkingDto")
    public ResponseEntity<ParkingDto> findById(@PathVariable Long id){
        ParkingDto parkingDto = parkingService.findById(id);
        return ResponseEntity.ok(parkingDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Find a parking by ID and update It.")
    public ResponseEntity<ParkingDto> updateById(@PathVariable Long id, @Valid @RequestBody UpdateParkingDto updateParkingDto){
        ParkingDto dto = parkingService.updateById(id,updateParkingDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Find a parking by ID and delete It.")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        parkingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/parkedTime/{id}")
    @ApiOperation("Calculate a minutes total between entrance and exit of parking.")
    public ResponseEntity<Integer> getFraction(@PathVariable Long id){
        return ResponseEntity.ok(parkingService.getFraction(id));
    }

    @PutMapping("/generateBill/{id}")
    @ApiOperation(value = "Find a parking by ID and get a bill of a parking. It return a ParkingDto with bill.")
    public ResponseEntity<ParkingDto> generateBill(@RequestParam(value = "price") Double price,
                                              @RequestParam(value = "adicional") Double adicional,
                                              @PathVariable Long id){
        return ResponseEntity.ok(parkingService.generateBill(price,id,adicional));
    }

}
