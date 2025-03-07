package org.example.api;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthExample extends BaseTest {

    private static ExtentTest test;


    @Test
    public void basicAuthTestCase(){
        // Set the base URI
//        test.info("test case started");
        RestAssured.baseURI = "https://postman-echo.com";

        // Perform GET request with Basic Authentication
        Response response = given()
                .auth().basic("postman", "password") // Basic Auth credentials
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200) // Verify status code
                .body("authenticated", equalTo(true)) // Verify response body
                .extract().response();

        // Print the response
        System.out.println("Response: " + response.asString());
    }
}
