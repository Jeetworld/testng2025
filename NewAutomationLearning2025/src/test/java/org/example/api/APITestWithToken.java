package org.example.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class APITestWithToken {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        // 🔹 Initialize Request Specification with Token
        requestSpec = given()
                .baseUri("https://api.example.com")
                .auth().oauth2(TokenManager.getAccessToken()) // Set token once
                .contentType("application/json");
    }

    @Test
    public void testGetUserInfo() {
        Response response = requestSpec.when().get("/user");

        // 🔄 Retry if token is expired
        if (response.getStatusCode() == 401) {
            System.out.println("⚠️ Token expired! Refreshing...");
            TokenManager.getAccessToken(); // Refresh token
            response = requestSpec.when().get("/user"); // Retry API
        }

        System.out.println("✅ Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "User API failed!");
    }

    @Test
    public void testGetTransactions() {
        Response response = requestSpec.when().get("/transactions");

        // 🔄 Retry if token is expired
        if (response.getStatusCode() == 401) {
            System.out.println("⚠️ Token expired! Refreshing...");
            TokenManager.getAccessToken(); // Refresh token
            response = requestSpec.when().get("/transactions"); // Retry API
        }

        System.out.println("✅ Transactions Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Transactions API failed!");
    }
}
