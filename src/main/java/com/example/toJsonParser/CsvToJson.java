package com.example.toJsonParser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToJson {
    @Value("${input}")
    private String input;

    @Value("${output}")
    private String output;

    public String parseCsvToJson(/*File input, File output*/) throws Exception {

        File in = new File(input);
        List<Map<?, ?>> data = readObjectsFromCsv(in);

        File out = new File(output);
        return writeAsJson(data, out, in);
    }

    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }

    public String writeAsJson(List<Map<?, ?>> data, File outFile, File inFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String name = inFile.getName();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mac", name.substring(name.lastIndexOf('-') + 1, name.length() - 4));
        map.put("received", name.substring(0, name.lastIndexOf('-')));

        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss");

        map.put("created", df.format(currentDate));
        map.put("payload", data);

        mapper.writeValue(outFile, map);
        String json = new ObjectMapper().writeValueAsString(map);

        return json;
    }
}
