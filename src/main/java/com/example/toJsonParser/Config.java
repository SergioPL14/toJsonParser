package com.example.toJsonParser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class Config {
    @Bean
    public CsvToJson csvToJson() {
        return new CsvToJson();
    }
}
