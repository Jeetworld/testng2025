package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BearerTokenHanling {

    private static String bearerToken; // Store token in a static variable

    @BeforeClass
    public void setUp() {
        // Set base URI
        RestAssured.baseURI = "https://api.example.com";

        // Fetch token once before running tests
        Response response = given()
                .contentType("application/json")
                .body("{ \"username\": \"user\", \"password\": \"pass\" }")
                .post("/auth/login")
                .then()
                .extract().response();

        // Extract and store token
        bearerToken = response.jsonPath().getString("token");
    }

    @Test
    public void testGetUserDetails() {
        given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/users/details")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetOrders() {
        given()
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .get("/orders")
                .then()
                .statusCode(200);
    }
}
