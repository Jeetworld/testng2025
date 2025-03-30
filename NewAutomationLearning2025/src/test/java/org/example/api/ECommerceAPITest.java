package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ECommerceAPITest {
    private static String authToken;
    private static int productId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.ecommerce.com"; // Replace with actual URL
    }

    // 1️⃣ Login and Extract Bearer Token
    @Test(priority = 1)
    public void testLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"testuser\", \"password\": \"password123\" }")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().response();

        authToken = response.jsonPath().getString("token");
        Assert.assertNotNull(authToken, "Token should not be null");
        System.out.println("Extracted Token: " + authToken);
    }

    // 2️⃣ Get Product List and Extract Product ID
    @Test(priority = 2, dependsOnMethods = "testLogin")
    public void testGetProducts() {
        Response response = given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("products-schema.json")) // Schema validation
                .extract().response();

        productId = response.jsonPath().getInt("products[0].id");
        Assert.assertTrue(productId > 0, "Product ID should be valid");
        System.out.println("Extracted Product ID: " + productId);
    }

    // 3️⃣ Add Product to Cart
    @Test(priority = 3, dependsOnMethods = "testGetProducts")
    public void testAddToCart() {
        given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body("{ \"productId\": " + productId + ", \"quantity\": 2 }")
                .when()
                .post("/cart/add")
                .then()
                .statusCode(201)
                .body("message", equalTo("Product added to cart"));
    }

    // 4️⃣ Validate Cart Contents
    @Test(priority = 4, dependsOnMethods = "testAddToCart")
    public void testVerifyCart() {
        given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/cart")
                .then()
                .statusCode(200)
                .body("items.productId", hasItem(productId))
                .body("items.quantity", hasItem(2));
    }
}
