package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.ParkingRepository;
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
public class ParkingServiceTest {

    @InjectMocks
    private ParkingService parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    private ParkingDto parkingDto;
    private Parking parking;
    private Parking parkingDiaria;
    private Parking parkingMensal;
    private NewParkingDto newParkingDto;
    private Long fakeId;
    private UpdateParkingDto updateParkingDto;

    @BeforeEach
    void setup(){
        parkingDto = new ParkingDto(1L,"WWW-9090","Ford Ka","Branco",
                LocalDateTime.now(),null, PeriodType.FRACIONADO,null);
        parking = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        parkingDiaria = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.DIARIA);
        parkingMensal = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.MENSAL);
        newParkingDto = new NewParkingDto("WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        updateParkingDto = new UpdateParkingDto(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        fakeId = 1L;
    }

    @Test
    @DisplayName("When called It must return a Parking")
    void returnTheConversionParkingDtoToParking(){
        Parking parking = parkingService.toParking(parkingDto);
        Assertions.assertEquals(parkingDto.getId(), parking.getId());
        Assertions.assertEquals(parkingDto.getLicense(),parking.getLicense());

    }

    @Test
    @DisplayName("When called It must return a ParkingDto")
    void returnTheConversionParkingToParkingDto(){
        ParkingDto parkingDto = parkingService.toParkingDto(parking);
        Assertions.assertEquals(parking.getId(),parkingDto.getId());
        Assertions.assertEquals(parking.getLicense(),parkingDto.getLicense());
        Assertions.assertEquals(parking.getColor(), parkingDto.getColor());
    }

    @Test
    @DisplayName("When called It must return a Parking.")
    void returnTheConversionNewParkingDtoToParking(){
        Parking parking = parkingService.toParking(newParkingDto);
        Assertions.assertEquals(newParkingDto.getLicense(),parking.getLicense());
        Assertions.assertEquals(newParkingDto.getModel(), parking.getModel());
        Assertions.assertEquals(newParkingDto.getPeriodType(),parking.getPeriodType());
    }

    @Test
    @DisplayName("When called It must return a Parking.")
    void returnTheConversionUpdateParkingDtoToParking(){
        Parking parking = parkingService.toParking(updateParkingDto);
        Assertions.assertEquals(updateParkingDto.getLicense(),parking.getLicense());
        Assertions.assertEquals(updateParkingDto.getModel(), parking.getModel());
        Assertions.assertEquals(updateParkingDto.getPeriodType(),parking.getPeriodType());
    }

    @Test
    @DisplayName("When called It must return a list of PrkingDto.")
    void returnListParkingDto(){
        List<Parking> parkingList = List.of(parking);
        Mockito.when(parkingRepository.findAll()).thenReturn(parkingList);
        List<ParkingDto> dtoList = parkingService.findAll();
        Assertions.assertEquals(parkingList.get(0).getId(),dtoList.get(0).getId());
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a NewParkingDto. Save a Parking and return a ParkingDto")
    void returnPrkingDto(){
        Mockito.when(parkingRepository.save(parking)).thenReturn(parking);
        ParkingDto parkingDtoSaved = parkingService.saveParking(newParkingDto);
        Assertions.assertEquals(parking.getModel(),parkingDtoSaved.getModel());
    }

    @Test
    @DisplayName("When called It must get a ID and return a ParkingDto.")
    void returnParkingDtoById(){
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.ofNullable(parking));
        ParkingDto dto = parkingService.findById(fakeId);
        Assertions.assertEquals(dto.getId(), parking.getId());
        Assertions.assertEquals(dto.getLicense(),parking.getLicense());
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.findById(11L));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    @DisplayName("When called It must get a ID, UpdateParkingDto and Return ParkingDto.")
    void shouldUpdateParkingAndReturnParkingDto(){
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.ofNullable(parking));
        Mockito.when(parkingRepository.save(parking)).thenReturn(parking);
        ParkingDto dto = parkingService.updateById(fakeId,updateParkingDto);
        Assertions.assertEquals(parking.getId(),dto.getId());
        Assertions.assertEquals(parking.getLicense(),dto.getLicense());
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.updateById(11L,updateParkingDto));
    }

    @Test
    @DisplayName("When called It must delete a Parking.")
    void shouldDeleteAParking(){
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.ofNullable(parking));
        Mockito.doNothing().when(parkingRepository).deleteById(fakeId);

        parkingService.deleteById(fakeId);

        Mockito.verify(parkingRepository, times(1)).findById(fakeId);
        Mockito.verify(parkingRepository,times(1)).deleteById(fakeId);
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.findById(11L));

    }

    @Test
    @DisplayName("It must return a time in minutes that the car stayed parked.")
    void shouldReturnMinutesParked(){
        Parking p = new Parking(1L,"WWW-9090","Ford Ka","Branco", PeriodType.FRACIONADO);
        LocalDateTime saida = LocalDateTime.now();
        p.setExit(saida);
        int minutosEsperado = (p.getExit().getHour() * 60 + p.getExit().getMinute()) - (p.getEntrance().getHour() * 60 + p.getEntrance().getMinute());

        Mockito.when(parkingRepository.findById(p.getId())).thenReturn(Optional.of(parking));
        Integer minutos = parkingService.getFraction(p.getId());

        Assertions.assertEquals(minutosEsperado,minutos);
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.getFraction(11L));

    }

    @Test
    @DisplayName("If PeriodType is Fracionado and bigger 60 minutes then It must return a ParkingDto with bill and adicional.")
    void shouldReturnParkingDtoWithBillFraction() {
        double price = 7.00;
        double adicional = 5.00;
        Parking p = new Parking(1L, "WWW-9090", "Ford Ka", "Branco", PeriodType.FRACIONADO);
        p.setExit(LocalDateTime.now().plusMinutes(61));
        p.setBill(12d);
        parking.setExit(LocalDateTime.now().plusMinutes(61));
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.of(parking));
        ParkingDto dto = parkingService.generateBill(price,fakeId,adicional);
        Assertions.assertEquals(p.getBill(),dto.getBill());
        Assertions.assertThrows(ObjectNotFoundException.class,()->parkingService.generateBill(price,11L,adicional));
    }

    @Test
    @DisplayName("If PeriodType is Fracionado and bigger 60 minutes then It must return a ParkingDto with bill with adicional.")
    void shouldReturnParkingDtoWithBillFractionWithoutAdicional() {
        double price = 9.00;
        double adicional = 5.00;
        Parking p = new Parking(1L, "WWW-9090", "Ford Ka", "Branco", PeriodType.FRACIONADO);
        p.setExit(LocalDateTime.now().plusMinutes(31));
        p.setBill(9d);
        parking.setExit(LocalDateTime.now().plusMinutes(31));
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.of(parking));
        ParkingDto dto = parkingService.generateBill(price,fakeId,adicional);
        Assertions.assertEquals(p.getBill(),dto.getBill());

    }

    @Test
    @DisplayName("If PeriodType is Diaria It must return a ParkingDto with bill.")
    void shouldReturnParkingDtoWithBill() {
        double price = 30.00;
        double adicional = 5.00;
        Parking p = new Parking(1L, "WWW-9090", "Ford Ka", "Branco", PeriodType.DIARIA);
        p.setExit(LocalDateTime.now());
        p.setBill(30d);
        parkingDiaria.setExit(LocalDateTime.now());
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.of(parkingDiaria));
        ParkingDto dto = parkingService.generateBill(price,fakeId,adicional);
        Assertions.assertEquals(p.getBill(),dto.getBill());

    }

    @Test
    @DisplayName("If PeriodType is Mensal It must return a ParkingDto with bill.")
    void shouldReturnParkingDtoWithBillMensal() {
        double price = 200.00;
        double adicional = 5.00;
        Parking p = new Parking(1L, "WWW-9090", "Ford Ka", "Branco", PeriodType.MENSAL);
        p.setExit(LocalDateTime.now());
        p.setBill(200d);
        parkingMensal.setExit(LocalDateTime.now());
        Mockito.when(parkingRepository.findById(fakeId)).thenReturn(Optional.of(parkingMensal));
        ParkingDto dto = parkingService.generateBill(price,fakeId,adicional);
        Assertions.assertEquals(p.getBill(),dto.getBill());

    }




}










