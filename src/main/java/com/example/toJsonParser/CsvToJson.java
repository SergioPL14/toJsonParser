package com.example.toJsonParser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvToJson {

    public static void main(String[] args) throws Exception {
        File input = new File("D:/dev/toJsonParser/files/2020-02-09T16.00.00-testfile3.csv");
        File output = new File("D:/dev/toJsonParser/files/out.json");

        List<Map<?, ?>> data = readObjectsFromCsv(input);
        writeAsJson(data, output, input);
    }

    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }

    public static void writeAsJson(List<Map<?, ?>> data, File outFile, File inFile) throws IOException {
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
    }
}
