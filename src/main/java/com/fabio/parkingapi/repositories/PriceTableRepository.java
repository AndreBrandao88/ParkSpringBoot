package com.fabio.parkingapi.repositories;

import com.fabio.parkingapi.entities.PriceTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceTableRepository extends JpaRepository<PriceTable,Long> {
}
