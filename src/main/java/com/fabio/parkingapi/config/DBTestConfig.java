package com.fabio.parkingapi.config;

import com.fabio.parkingapi.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DBTestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBaseTest(){
        dbService.instantiateDataBases();
        return true;
    }
}
