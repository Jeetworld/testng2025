package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.utils.ExtentReportManager;
import org.testng.annotations.Test;

public class ExtentReportSmapleTC extends BaseTest {

    private static ExtentTest test;

    @Test
    public void testLogin() {
        test = ExtentReportManager.createTest("Login Test");
        test.info("Starting login test");
        test.pass("Login test passed successfully");
    }
}
