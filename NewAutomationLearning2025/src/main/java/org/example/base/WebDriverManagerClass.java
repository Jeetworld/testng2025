package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverManagerClass {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // Thread-safe WebDriver instance

    // Method to initialize WebDriver based on browser type
    public static void initDriver(String browser) {
        if (driver.get() == null) {  // Prevent re-initialization
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Basic WebDriver configurations
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    // Method to get the current WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Method to close WebDriver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // Remove reference to avoid memory leaks
        }
    }
}
