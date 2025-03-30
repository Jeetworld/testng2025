package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class RestAssuredMultipleTests {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() throws FileNotFoundException {
        // Set Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Custom Logging Filter to Mask Sensitive Data
//        Filter sensitiveFilter = new RequestLoggingFilter(new PrintStream(String.valueOf(new StringWriter()))) {
//            private final Set<String> sensitiveHeaders = Collections.singleton("Authorization");
//
//            @Override
//            public void filter(io.restassured.filter.FilterableRequestSpecification requestSpec, io.restassured.filter.FilterableResponseSpecification responseSpec, io.restassured.filter.FilterContext ctx) {
//                requestSpec.removeHeaders(sensitiveHeaders); // Remove sensitive headers before logging
//                super.filter(requestSpec, responseSpec, ctx);
//            }
//        };

        // Define Request Specification with Logging Filter
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer SECRET_TOKEN") // Sensitive Data
//                .addFilter(sensitiveFilter) // Block sensitive info from logging
                .addFilter(new ResponseLoggingFilter()) // Logs response details
                .build();

        // Define Response Specification with JSON Schema Validation and Response Time Check
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectHeader("Content-Type", "application/json; charset=utf-8")
                .expectResponseTime(lessThan(2000L)) // Ensure response time < 2 sec
                .build();
    }

    // Test for GET request with JSON Schema Validation
    @Test
    public void testGetUser() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .spec(responseSpec)
                .assertThat()
                .body("id", equalTo(1))
                .body("username", equalTo("Bret"))
                .body(matchesJsonSchemaInClasspath("user-schema.json")); // JSON Schema Validation
    }

    // Test for POST request (Create User) with JSON Schema Validation
    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John Doe\", \"username\": \"johndoe\", \"email\": \"john@example.com\" }";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201) // Expect Created
                .body("name", equalTo("John Doe"))
                .body("username", equalTo("johndoe"))
                .time(lessThan(2000L)) // Response time validation
                .body(matchesJsonSchemaInClasspath("user-schema.json")); // Validate JSON Schema
    }
}
