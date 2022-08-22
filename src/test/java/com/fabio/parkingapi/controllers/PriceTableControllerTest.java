package com.fabio.parkingapi.controllers;

import com.fabio.parkingapi.dtos.NewPriceTableDto;
import com.fabio.parkingapi.dtos.PriceTableDto;
import com.fabio.parkingapi.dtos.UpdatePriceTableDto;
import com.fabio.parkingapi.services.PriceTableService;
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
public class PriceTableControllerTest {

    private static final String VERSION_APP = "v1";

    @Mock
    private PriceTableService priceTableService;

    @InjectMocks
    private PriceTableController priceTableController;

    private PriceTableDto priceTableDto;
    private NewPriceTableDto newPriceTableDto;
    private UpdatePriceTableDto updatePriceTableDto;
    private Long fakeId;

    @BeforeEach
    void setup(){
        priceTableDto = new PriceTableDto(1L,"Até 15 minutos",5.00, LocalDateTime.now());
        newPriceTableDto = new NewPriceTableDto("Até 15 minutos",5.00);
        updatePriceTableDto = new UpdatePriceTableDto(null,"Até 15 minutos",5.00);
        fakeId = 1L;
    }

    @Test
    @DisplayName("It must return status OK and a List of PriceTableDto.")
    void returnResponseListOfPriceTableDto() throws Exception {
        List<PriceTableDto> dtoList = List.of(priceTableDto);
        Mockito.when(priceTableService.findAll()).thenReturn(dtoList);
        var response = priceTableController.findAll();
        Assertions.assertEquals(ResponseEntity.ok(dtoList),response);
    }

    @Test
    @DisplayName("It must return status CREATED and a body with PriceTableDto.")
    void returnResponseStatusCreatedAndBodyOfPriceTableDto() throws Exception {
        Mockito.when(priceTableService.save(newPriceTableDto)).thenReturn(priceTableDto);
        var response = priceTableController.save(newPriceTableDto);
        Assertions.assertEquals(response.getStatusCode().value(), HttpStatus.CREATED.value());
        Assertions.assertEquals(priceTableDto,response.getBody());
    }

    @Test
    @DisplayName("It must return status OK and a body with PriceTableDto")
    void returnResponseStatusOkAndBodyOfPriceTableDto() throws Exception {
        Mockito.when(priceTableService.findById(fakeId)).thenReturn(priceTableDto);
        var response = priceTableController.findById(fakeId);
        Assertions.assertEquals(ResponseEntity.ok().body(priceTableDto),response);
    }

    @Test
    @DisplayName("When called It must update a PriceTable and return status ok with body of PriceTableDto.")
    void shouldReturnResponseStatusOkAndResponseBodyOfPriceTableDto() throws Exception {
        Mockito.when(priceTableService.updateById(fakeId,updatePriceTableDto)).thenReturn(priceTableDto);
        var response = priceTableController.updateById(fakeId,updatePriceTableDto);
        Assertions.assertEquals(ResponseEntity.ok(priceTableDto),response);
    }

    @Test
    @DisplayName("When called It must delete PriceTable and return status NoContent.")
    void shouldDeletePriceTable() throws Exception {
        Mockito.doNothing().when(priceTableService).deleteById(fakeId);
        var response = priceTableController.deleteById(fakeId);
        Assertions.assertEquals(ResponseEntity.noContent().build(),response);
    }

}
