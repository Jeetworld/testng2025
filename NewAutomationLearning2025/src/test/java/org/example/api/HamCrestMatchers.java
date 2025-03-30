package org.example.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamCrestMatchers {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        requestSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com") // Sample API
                .contentType("application/json");
    }

    @Test
    public void testValidateUserAPI() {
        requestSpec
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1)) // ID must be 1
                .body("name", notNullValue()) // Name should not be null
                .body("email", containsString("@")) // Email must contain '@'
                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}")); // Zipcode matches format 12345-6789
    }

    @Test
    public void testValidatePostsAPI() {
        requestSpec
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(50)) // More than 50 posts
                .body("userId", hasItem(1)) // At least one post from user 1
                .body("title", hasItems("qui est esse", "eum et est occaecati")); // Titles exist in the response
    }

    @Test
    public void testValidateSinglePost() {
        requestSpec
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", not(emptyOrNullString())) // Title should not be empty or null
                .body("body", containsString("quia et suscipit")) // Body contains specific text
                .body("userId", greaterThan(0)); // userId should be positive
    }
}
