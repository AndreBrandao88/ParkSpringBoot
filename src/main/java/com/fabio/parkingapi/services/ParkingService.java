package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewParkingDto;
import com.fabio.parkingapi.dtos.ParkingDto;
import com.fabio.parkingapi.dtos.UpdateParkingDto;
import com.fabio.parkingapi.entities.Parking;
import com.fabio.parkingapi.entities.enums.PeriodType;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public List<ParkingDto> findAll(){
        return parkingRepository.findAll().stream().map(this::toParkingDto).collect(Collectors.toList());
    }

    public ParkingDto saveParking(NewParkingDto newParkingDto){
        Parking parking = toParking(newParkingDto);
        parkingRepository.save(parking);
        return toParkingDto(parking);
    }

    public ParkingDto findById(Long id){
        Parking parking = parkingRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Parking not found with id -> " +id));
        return toParkingDto(parking);
    }

    public ParkingDto updateById(Long id, UpdateParkingDto updateParkingDto){
        findById(id);
        Parking parking = toParking(updateParkingDto);
        parkingRepository.save(parking);
        return toParkingDto(parking);
    }

    public void deleteById(Long id){
        findById(id);
        parkingRepository.deleteById(id);
    }

    public int getFraction(Long id){
        Parking parking =  parkingRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Parking not found with id -> "+id));
        LocalDateTime exit = LocalDateTime.now();
        parking.setExit(exit);
        return (exit.getHour() * 60 + exit.getMinute()) - (parking.getEntrance().getHour() * 60 + parking.getEntrance().getMinute());

    }

    public ParkingDto generateBill(Double price, Long id, Double adicional){
        Parking parking =  parkingRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Parking not found with id -> "+id));
        if(parking.getExit() == null){
            parking.setExit(LocalDateTime.now());
        }
        if(parking.getPeriodType().equals(PeriodType.FRACIONADO)){
            Integer totalParking = (parking.getExit().getHour() * 60 + parking.getExit().getMinute()) - (parking.getEntrance().getHour() * 60 + parking.getEntrance().getMinute());
            if (totalParking > 60){
                int horas = totalParking / 60;
                double totalAdicional = horas * adicional;
                parking.setBill(price + totalAdicional);
                parkingRepository.save(parking);
            }else {
                parking.setBill(price);
                parkingRepository.save(parking);
            }

            return toParkingDto(parking);
        }else if (parking.getPeriodType().equals(PeriodType.DIARIA)){
            parking.setBill(price);
            parkingRepository.save(parking);
            return toParkingDto(parking);
        }

        parking.setBill(price);
        parkingRepository.save(parking);
        return toParkingDto(parking);

    }



    public Parking toParking(ParkingDto dto){
        return new Parking(dto.getId(), dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public Parking toParking(NewParkingDto dto){
        return new Parking(null, dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public Parking toParking(UpdateParkingDto dto){
        return new Parking(dto.getId(), dto.getLicense(), dto.getModel(), dto.getColor(), dto.getPeriodType());
    }

    public ParkingDto toParkingDto(Parking parking){
        return new ParkingDto(parking.getId(),parking.getLicense(), parking.getModel(), parking.getColor(),
                parking.getEntrance(),parking.getExit(),parking.getPeriodType(),parking.getBill());
    }

}
