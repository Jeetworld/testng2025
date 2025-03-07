package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BearerTokenExample {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://api.github.com";

        // Perform GET request with Bearer Token Authentication
        Response response = given()
                .header("Authorization", "Basic " + "token")        //method 1 to add bearer token
                .auth().oauth2("your_github_token") // method 2 to add bearer token
                .when()
                .get("/user")
                .then()
                .statusCode(200) // Verify status code
                .body("login", equalTo("your_github_username")) // Verify response body
                .extract().response();

        // Print the response
        System.out.println("Response: " + response.asString());
    }
}
