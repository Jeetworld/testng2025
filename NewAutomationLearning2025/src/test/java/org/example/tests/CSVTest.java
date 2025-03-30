package org.example.tests;

import org.example.utils.CSVUtils;
import org.testng.annotations.Test;

import java.util.List;

public class CSVTest {

    @Test
    public void testCSVData() {
        String filePath = "src/resources/testdata/CsvTestData.csv";
        List<String[]> data = CSVUtils.readCSV(filePath);

        for (String[] row : data) {
            System.out.println("Username: " + row[0] + ", Password: " + row[1]);
        }
    }
}
