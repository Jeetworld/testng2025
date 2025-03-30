package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.utils.ExtentTestManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.example.base.WebDriverManagerClass.getDriver;

@Listeners(org.example.listeners.TestListener.class)
public class ListenerDemoTest extends BaseTest {

    private WebDriver driver;

    @Test
    public void testPass() {
        System.out.println("Test passed ✅");
        Assert.assertTrue(true);
    }

    @Test
    public void testFail() {
        System.out.println("Test failed ❌");
        Assert.assertTrue(false);
    }

    @Test
    public void testSkip() {
        System.out.println("Test skipped ⏩");
        throw new SkipException("Skipping this test intentionally");
    }

    @Test
    public void methodOne(){

        driver = getDriver();
        driver.get("https://example.com/login");
    }

    @Test
    public void customLoggingTest() {
        ExtentTest test = ExtentTestManager.getTest(); // 👈 get current test instance
        test.info("Starting test scenario...");

        // your test code
        test.pass("Step 1 completed");
        test.info("Verifying login section");
        test.fail("Something went wrong");
    }
}
