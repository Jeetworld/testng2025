package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PreemptiveAuthExample {
    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://httpbin.org";

        // Perform GET request with Preemptive Basic Authentication
        Response response = given()
                .auth().preemptive().basic("user", "passwd") // Preemptive Basic Auth credentials
                .when()
                .get("/basic-auth/user/passwd")
                .then()
                .statusCode(200) // Verify status code
                .body("authenticated", equalTo(true)) // Verify response body
                .extract().response();

        // Print the response
        System.out.println("Response: " + response.asString());
    }
}
