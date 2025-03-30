package org.example.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.utils.ExtentReportManager;
import org.example.utils.ExtentTestManager;
import org.example.utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class BaseTest {
    protected WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    protected static Properties config;

    protected static Logger logger;

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @BeforeSuite
    public void setupSuite() {
        System.out.println("Starting Test Suite...");
        extent = ExtentReportManager.getInstance();
        test = ExtentReportManager.createTest("Before Suite Code Executed");
        test.info("Executing before suite");
        test.pass("Executing before suite...");

        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/resources/config/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Before Suite - Initializing Log4j");

        // Initialize Log4j
        logger = Logger.getLogger(BaseTest.class);
        PropertyConfigurator.configure("src/resources/config/log4j.properties");

        logger.info("Logging is set up successfully.");


    }

    @BeforeClass
    @Parameters("browser") // Allows passing browser name from TestNG XML
    public void setupClass(@Optional("chrome") String browser) {
        System.out.println("Initializing WebDriver for " + browser);
        WebDriverManagerClass.initDriver(browser);
        driver = WebDriverManagerClass.getDriver();
        ScreenshotUtils.setDriver(driver); // 👈 this line is important
        driverThread.set(driver);
    }

    // Create new ExtentTest per method
    @BeforeMethod(alwaysRun = true)
    public void createExtentTest(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        testThread.set(test);
        ExtentTestManager.setTest(test);
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("Closing WebDriver...");
        WebDriverManagerClass.quitDriver();
        extent.flush();
    }

    // Utility to get current driver instance
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    // Utility to get current ExtentTest logger instance
    public static ExtentTest getLogger() {
        return testThread.get();
    }

    @AfterSuite
    public void tearDownSuite() {
        System.out.println("Test Suite Execution Completed.");
    }
}
