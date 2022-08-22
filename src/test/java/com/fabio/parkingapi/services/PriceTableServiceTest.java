package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewPriceTableDto;
import com.fabio.parkingapi.dtos.PriceTableDto;
import com.fabio.parkingapi.dtos.UpdatePriceTableDto;
import com.fabio.parkingapi.entities.PriceTable;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.PriceTableRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PriceTableServiceTest {

    @InjectMocks
    private PriceTableService priceTableService;

    @Mock
    private PriceTableRepository priceTableRepository;

    private PriceTable priceTable;
    private PriceTableDto priceTableDto;
    private NewPriceTableDto newPriceTableDto;
    private UpdatePriceTableDto updatePriceTableDto;
    private Long fakeId;

    @BeforeEach
    void setup(){
        priceTable = new PriceTable(null,"Até 15 minutos",5.00);
        priceTableDto = new PriceTableDto(1L,"Até 15 minutos",5.00, LocalDateTime.now());
        newPriceTableDto = new NewPriceTableDto("Até 15 minutos",5.00);
        updatePriceTableDto = new UpdatePriceTableDto(null,"Até 15 minutos",5.00);
        fakeId = 1L;
    }

    @Test
    @DisplayName("When called It must do a conversion PriceTable to PriceTableDto.")
    void shouldReturnPriceTableDto(){
        PriceTableDto dto = priceTableService.toPriceTableDto(priceTable);
        Assertions.assertEquals(priceTableDto.getDescription(),dto.getDescription());
        Assertions.assertEquals(priceTableDto.getPrice(),dto.getPrice());
    }

    @Test
    @DisplayName("When called It must do a conversion de NewPriceTableDto to PriceTable.")
    void shouldReturnPriceTable(){
        PriceTable p = priceTableService.toPriceTable(newPriceTableDto);
        PriceTable p2 = priceTableService.toPriceTable(updatePriceTableDto);
        Assertions.assertEquals(priceTable.getDescription(),p.getDescription());
        Assertions.assertEquals(priceTable.getPrice(),p.getPrice());
        Assertions.assertEquals(priceTable.getDescription(),p2.getDescription());
        Assertions.assertEquals(priceTable.getPrice(),p2.getPrice());
    }

    @Test
    @DisplayName("When called It must return a list of PriceTableDto.")
    void returnListPriceTableDto(){
        List<PriceTable> priceTableList = List.of(priceTable);
        Mockito.when(priceTableRepository.findAll()).thenReturn(priceTableList);
        List<PriceTableDto> dtoList = priceTableService.findAll();
        Assertions.assertEquals(priceTableList.get(0).getId(),dtoList.get(0).getId());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a NewPriceTableDto. Save a PriceTable and return a PriceTableDto")
    void shouldReturnSavePriceTableAndReturnPriceTableDto(){
        Mockito.when(priceTableRepository.save(priceTable)).thenReturn(priceTable);
        PriceTableDto priceTableDtoSaved = priceTableService.save(newPriceTableDto);
        Assertions.assertEquals(priceTable.getId(),priceTableDtoSaved.getId());
        Assertions.assertEquals(priceTable.getDescription(),priceTableDtoSaved.getDescription());
    }

    @Test
    @DisplayName("When called It must get a ID and return a PriceTableDto.")
    void shouldFindPriceTableById(){
        Mockito.when(priceTableRepository.findById(fakeId)).thenReturn(Optional.ofNullable(priceTable));
        PriceTableDto dto = priceTableService.findById(fakeId);
        Assertions.assertEquals(dto.getId(), priceTable.getId());
        Assertions.assertEquals(dto.getDescription(),priceTable.getDescription());
        Assertions.assertThrows(ObjectNotFoundException.class,()->priceTableService.findById(11L));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a ID, UpdatePriceTableDto and Return PriceTableDto.")
    void shouldUpdatePriceTableAndReturnPriceTableDto(){
        Mockito.when(priceTableRepository.findById(fakeId)).thenReturn(Optional.ofNullable(priceTable));
        Mockito.when(priceTableRepository.save(priceTable)).thenReturn(priceTable);
        PriceTableDto dto = priceTableService.updateById(fakeId,updatePriceTableDto);
        Assertions.assertEquals(priceTable.getId(),dto.getId());
        Assertions.assertEquals(priceTable.getDescription(),dto.getDescription());
        Assertions.assertThrows(ObjectNotFoundException.class,()->priceTableService.updateById(11L,updatePriceTableDto));
    }

    @Test
    @DisplayName("When called It must delete a PriceTable.")
    void shouldDeleteAPriceTable(){
        Mockito.when(priceTableRepository.findById(fakeId)).thenReturn(Optional.ofNullable(priceTable));
        Mockito.doNothing().when(priceTableRepository).deleteById(fakeId);

        priceTableService.deleteById(fakeId);

        Mockito.verify(priceTableRepository, times(1)).findById(fakeId);
        Mockito.verify(priceTableRepository,times(1)).deleteById(fakeId);
        Assertions.assertThrows(ObjectNotFoundException.class,()->priceTableService.findById(11L));

    }




}
