package com.backend.currencyexchange.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@Component
@NoArgsConstructor
public class CSVReaderUtil {

    public HashMap<String, BigDecimal> read(String fileName) throws CsvValidationException, IOException {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getPath();
        CSVReader csvReader = new CSVReader(new FileReader(path));
        HashMap<String, BigDecimal> records = new HashMap<>();
        String[] values;
        while ((values = csvReader.readNext()) != null) {
            records.put(values[0], new BigDecimal(values[1]));
        }
        return records;
    }
}
