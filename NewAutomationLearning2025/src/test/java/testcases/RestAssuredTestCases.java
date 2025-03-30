package testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.given;

public class RestAssuredTestCases {

        private static String bearerToken;

        @BeforeClass
        public void setup() {
            // Base URI for API
            RestAssured.baseURI = "https://api.example.com";

            // Fetch Bearer Token
            Response response =
                    given()
                        .contentType(ContentType.JSON)
                        .body("{\"username\":\"testuser\",\"password\":\"password123\"}")
                    .when()
                        .post("/auth/token");

            Assert.assertEquals(response.getStatusCode(), 200, "Token fetch failed");
            bearerToken = response.jsonPath().getString("token");
            Assert.assertNotNull(bearerToken, "Bearer token is null");
        }

        @Test(priority = 1)
        public void testGetUserDetails() {
            Response response = given()
                    .header("Authorization", "Bearer " + bearerToken)
                    .when()
                    .get("/users/123");

            Assert.assertEquals(response.getStatusCode(), 200, "Failed to fetch user details");
            Assert.assertNotNull(response.jsonPath().getString("id"), "User ID is null");
        }

        @Test(priority = 2)
        public void testCreateUser() {
            Response response = given()
                    .header("Authorization", "Bearer " + bearerToken)
                    .contentType("application/json")
                    .body("{\"name\":\"John Doe\",\"email\":\"john@example.com\"}")
                    .when()
                    .post("/users");

            Assert.assertEquals(response.getStatusCode(), 201, "User creation failed");
            Assert.assertNotNull(response.jsonPath().getString("id"), "Created user ID is null");
        }

        @Test(priority = 3, dependsOnMethods = "testCreateUser")
        public void testDeleteUser() {
            Response response = given()
                    .header("Authorization", "Bearer " + bearerToken)
                    .when()
                    .delete("/users/123");

            Assert.assertEquals(response.getStatusCode(), 204, "User deletion failed");
        }

    @Test(priority = 1, invocationCount = 10, threadPoolSize = 2)
    public void testAPIResponseTime() {
        long maxResponseTime = 2000; // 2 seconds threshold

        Response response = given()
                .when()
                .get("/users");

        long responseTime = response.getTime(); // Get response time in milliseconds

        System.out.println("Response Time: " + responseTime + " ms");
        Assert.assertTrue(responseTime < maxResponseTime, "Response time exceeded limit!");
    }
    }

