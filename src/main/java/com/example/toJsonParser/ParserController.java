package com.example.toJsonParser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;

@Controller
@EnableAutoConfiguration
public class ParserController {
    @Value("${input}") String inputPath;
    File input = new File(inputPath);
    @Value("${output}") String outputPath;
    File output = new File(outputPath);

    private final CsvToJson parser;

    ParserController(CsvToJson parser) {
        this.parser = parser;
    }

    @GetMapping("/ftp")
    String getJsonFile() throws Exception {
        return parser.parseCsvToJson(input, output);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ParserController.class, args);
    }
}
