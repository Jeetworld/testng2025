package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DigestAuthExample {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://httpbin.org";

        // Perform GET request with Digest Authentication
        Response response = given()
                .auth().digest("user", "passwd") // Digest Auth credentials
                .when()
                .get("/digest-auth/auth/user/passwd")
                .then()
                .statusCode(200) // Verify status code
                .body("authenticated", equalTo(true)) // Verify response body
                .extract().response();

        // Print the response
        System.out.println("Response: " + response.asString());
    }
}