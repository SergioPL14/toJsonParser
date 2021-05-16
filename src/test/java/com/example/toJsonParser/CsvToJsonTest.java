package com.example.toJsonParser;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(
        classes = ToJsonParserApplication.class)
public class CsvToJsonTest {

    @Autowired
    private CsvToJson parser;

    @Value("${input}") String inputPath;
    File input = new File(inputPath);

    @Value("${output}") String outputPath;
    File output = new File(outputPath);

    @Test
    public void fileHasCorrectStructure() throws Exception {
        String outFile = parser.parseCsvToJson(input, output);

        System.out.println("FILE: " + output);
        assertTrue(outFile.contains("mac"));
        assertTrue(outFile.contains("created"));
        assertTrue(outFile.contains("payload"));
        assertTrue(outFile.contains("received"));
    }
}