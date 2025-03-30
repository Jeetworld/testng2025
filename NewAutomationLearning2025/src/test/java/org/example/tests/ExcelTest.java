package org.example.tests;

import org.example.utils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelTest {

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() {
        String filePath = "src/resources/testdata/RegressionTestData.xlsx";
        return ExcelUtils.getAllData(filePath, "Sheet1");
    }

    @Test(dataProvider = "excelData")
    public void testExcelData(String username, String password) {
        System.out.println("Username: " + username + ", Password: " + password);
    }
}
