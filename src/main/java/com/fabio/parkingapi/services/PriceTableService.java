package com.fabio.parkingapi.services;

import com.fabio.parkingapi.dtos.NewPriceTableDto;
import com.fabio.parkingapi.dtos.PriceTableDto;
import com.fabio.parkingapi.dtos.UpdatePriceTableDto;
import com.fabio.parkingapi.entities.PriceTable;
import com.fabio.parkingapi.exceptions.ObjectNotFoundException;
import com.fabio.parkingapi.repositories.PriceTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceTableService {

    @Autowired
    private PriceTableRepository priceTableRepository;

    public List<PriceTableDto> findAll(){
        return priceTableRepository.findAll().stream().map(this::toPriceTableDto).collect(Collectors.toList());
    }

    public PriceTableDto save(NewPriceTableDto dto){
        PriceTable priceTable = toPriceTable(dto);
        priceTableRepository.save(priceTable);
        return toPriceTableDto(priceTable);
    }

    public PriceTableDto findById(Long id){
        PriceTable priceTable = priceTableRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Price Table not found with id -> "+id));
        return toPriceTableDto(priceTable);
    }

    public PriceTableDto updateById(Long id, UpdatePriceTableDto dto){
        findById(id);
        PriceTable priceTable = toPriceTable(dto);
        priceTableRepository.save(priceTable);
        return toPriceTableDto(priceTable);
    }

    public void deleteById(Long id){
        findById(id);
        priceTableRepository.deleteById(id);
    }


    public PriceTableDto toPriceTableDto(PriceTable p){
        return new PriceTableDto(p.getId(),p.getDescription(), p.getPrice(),p.getCreation());
    }

    public PriceTable toPriceTable(NewPriceTableDto dto){
        return new PriceTable(null,dto.getDescription(), dto.getPrice());
    }

    public PriceTable toPriceTable(UpdatePriceTableDto dto){
        return new PriceTable(dto.getId(), dto.getDescription(), dto.getPrice());
    }


}
