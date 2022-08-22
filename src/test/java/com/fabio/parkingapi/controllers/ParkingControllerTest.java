package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.services.ParkingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ParkingControllerTest {

    private static final String VERSION_APP = "v1";

    @Mock
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    private ParkingDto parkingDto;
    private NewParkingDto newParkingDto;
    private UpdateParkingDto updateParkingDto;
    private Long fakeId;

    @BeforeEach
    void setup(){
      parkingDto = new ParkingDto(7L,"WWW9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
      newParkingDto = new NewParkingDto("WWW9090","Ford Ka","Branco", PeriodType.FRACIONADO);
      updateParkingDto = new UpdateParkingDto(7L,"WWW9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        fakeId = 7L;
    }

    @Test
    @DisplayName("It must return status OK and a List of ParkingDto")
    void returnResponseListOfParkingDto() throws Exception {
        List<ParkingDto> dtoList = List.of(parkingDto);
        Mockito.when(parkingService.findAll()).thenReturn(dtoList);
        var response = parkingController.findALl();
        Assertions.assertEquals(ResponseEntity.ok(dtoList),response);
    }

    @Test
    @DisplayName("It must return status CREATED and a body with ParkingDto.")
    void returnResponseStatusCreatedAndBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.saveParking(newParkingDto)).thenReturn(parkingDto);
        var response = parkingController.saveParking(newParkingDto);
        Assertions.assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        Assertions.assertEquals(parkingDto,response.getBody());
    }

    @Test
    @DisplayName("It must return status OK and a body with ParkingDto")
    void returnResponseStatusOkAndBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.findById(1L)).thenReturn(parkingDto);
        var response = parkingController.findById(1L);
        Assertions.assertEquals(ResponseEntity.ok().body(parkingDto),response);
    }

    @Test
    @DisplayName("When called It must update a Parking and return status ok with body of ParkingDto")
    void shouldReturnResponseStatusOkAndResponseBodyOfParkingDto() throws Exception {
        Mockito.when(parkingService.updateById(fakeId,updateParkingDto)).thenReturn(parkingDto);
        var response = parkingController.updateById(fakeId,updateParkingDto);
        Assertions.assertEquals(ResponseEntity.ok(parkingDto),response);

    }

    @Test
    @DisplayName("When called It must delete Parking and return status NoContent.")
    void shouldDeleteParking() throws Exception {
        Mockito.doNothing().when(parkingService).deleteById(fakeId);
        var response = parkingController.deleteById(fakeId);
        Assertions.assertEquals(ResponseEntity.noContent().build(),response);
    }

    @Test
    @DisplayName("It must return status OK and body with time parked in minutes.")
    void shouldReturnTimeParked() throws Exception {
        Mockito.when(parkingService.getFraction(fakeId)).thenReturn(100);
        var response = parkingController.getFraction(fakeId);
        Assertions.assertEquals(ResponseEntity.ok(100),response);
    }

    @Test
    @DisplayName("It must return status ok and body with bill generated of Parking.")
    void shouldReturnParkingDtoAndGenerateBill() throws Exception {
        parkingDto.setExit(LocalDateTime.now());
        parkingDto.setBill(12.00);
        Mockito.when(parkingService.generateBill(12d,7L,5d)).thenReturn(parkingDto);
        var response = parkingController.generateBill(12d,5.00,fakeId);
        Assertions.assertEquals(ResponseEntity.ok(parkingDto),response);
    }


}
