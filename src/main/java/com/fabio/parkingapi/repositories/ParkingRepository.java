package com.fabio.parkingapi.repositories;

import com.fabio.parkingapi.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Long> {
}
