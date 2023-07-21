package com.pinkieyun.fitnesscenter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModalMapperConfiguration {
    
    @Bean
    public ModelMapper initModelMapper() {
        return new ModelMapper();
    }
    
}