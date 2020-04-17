package com.tonydpadua.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@AllArgsConstructor
@Configuration
@Profile("dev")
public class DevConfig {

    private DbService dbService;

    //@Value("${spring.jpa.hibernate.ddl-auto}")
   // private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
/*
        if(!"create".equals(strategy)){
            return false;
        }


 */
        dbService.instantiateTestDatabase();

        return true;
    }
}
