package com.fabio.parkingapi.repositories;

import com.fabio.parkingapi.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Transactional(readOnly = true)
    UserModel findByEmail(String email);
}
