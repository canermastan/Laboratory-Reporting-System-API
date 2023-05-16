package com.canermastan.laboratoryreportingsystemapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableCaching
@SpringBootApplication
public class LaboratoryReportingSystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryReportingSystemApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
