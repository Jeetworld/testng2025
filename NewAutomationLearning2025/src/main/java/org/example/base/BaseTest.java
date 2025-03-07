package org.example.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.utils.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BaseTest {
    protected WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    protected static Properties config;

    protected static Logger logger;

    @BeforeSuite
    public void setupSuite() {
        System.out.println("Starting Test Suite...");
        extent = ExtentReportManager.getInstance();
        test = ExtentReportManager.createTest("Before Suite Code Executed");
        test.info("Executing before suite");
        test.pass("Executing before suite...");

        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Before Suite - Initializing Log4j");

        // Initialize Log4j
        logger = Logger.getLogger(BaseTest.class);
        PropertyConfigurator.configure("src/test/resources/log4j.properties");

        logger.info("Logging is set up successfully.");

    }

    @BeforeClass
    @Parameters("browser") // Allows passing browser name from TestNG XML
    public void setupClass(@Optional("chrome") String browser) {
        System.out.println("Initializing WebDriver for " + browser);
        WebDriverManagerClass.initDriver(browser);
        driver = WebDriverManagerClass.getDriver();
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("Closing WebDriver...");
        WebDriverManagerClass.quitDriver();
        extent.flush();
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("Test Suite Execution Completed.");
    }
}
