package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.base.BaseTest;
import org.example.utils.APIUtils;
import io.restassured.response.Response;
import org.example.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APIEmployeeTest extends BaseTest {
    private static ExtentTest test;

    @Test
    public void testCreateEmployee() throws IOException {
        test = ExtentReportManager.createTest("Login Test");
        test.info("Starting login test");
        test.pass("Login test passed successfully");

        String endpoint = "https://reqres.in/api/users";
        String jsonBody = new String(Files.readAllBytes(Paths.get("src/resources/payloads/createEmployeePayload.json")));

        test.info("hit the api");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Response response = APIUtils.sendPostRequest(endpoint, jsonBody, headers);


        test.info("verifying the response");
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getBody().asString().contains("John Doe"));
    }
}
