package com.example.toJsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParserController {

    @Autowired
    private final CsvToJson parser;

    public ParserController(CsvToJson parser) {
        this.parser = parser;
    }

    @GetMapping(value = "/ftp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getJsonFile() throws Exception {
        String jsonFile = parser.parseCsvToJson();
        System.out.println("FILE: " + jsonFile);
        return jsonFile;
    }
}
