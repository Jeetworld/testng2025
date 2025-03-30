// Sample Parallel Test Class - LoginTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.pageobjects.LoginPage;
import org.testng.annotations.Test;

public class paralleltesting extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {
        getLogger().info("Thread ID: " + Thread.currentThread().getId());
        getLogger().info("Opening login page");

        getDriver().get("https://example.com/login");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername("admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        getLogger().pass("Login with valid credentials passed");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        getLogger().info("Thread ID: " + Thread.currentThread().getId());
        getLogger().info("Opening login page");

        getDriver().get("https://example.com/login");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("wrongPass");
        loginPage.clickLogin();

        getLogger().pass("Login with invalid credentials test executed");
    }
}
