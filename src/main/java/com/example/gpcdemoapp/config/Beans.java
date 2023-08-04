package com.example.gpcdemoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

/**
 * @author aleksandr on 04.08.2023
 * @projectName GPCdemoApp
 */
@Component
public class Beans {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
