package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.pageobjects.LoginPage;
import org.example.utils.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.base.WebDriverManagerClass.getDriver;

public class LoginTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private static ExtentTest test;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        driver.get("https://example.com/login");
        loginPage = new LoginPage(driver);
         test = ExtentReportManager.createTest("Valid Login Test");
    }

    @Test(priority = 1)
    public void testValidLogin() {
        test.info("Starting login test");
        loginPage.login("testuser", "Test@123");
        String expectedUrl = "https://example.com/dashboard";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login failed with valid credentials");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        test.info("Starting login test");
        loginPage.login("invalidUser", "wrongPass");
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for invalid login");
    }

    @Test(priority = 3)
    public void testEmptyCredentials() {
        test.info("Starting login test");
        loginPage.login("", "");
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for empty credentials");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
