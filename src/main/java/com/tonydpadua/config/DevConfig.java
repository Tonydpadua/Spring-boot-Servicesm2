package com.tonydpadua.config;

import com.tonydpadua.email.EmailService;
import com.tonydpadua.email.SmptEmailService;
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

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmptEmailService();
    }
}
