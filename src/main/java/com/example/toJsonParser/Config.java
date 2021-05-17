package com.example.toJsonParser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CsvToJson csvToJson() {
        return new CsvToJson();
    }
}
