package com.fabio.parkingapi.repositories;

import com.fabio.parkingapi.entities.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository <EmailModel, Long> {
}
