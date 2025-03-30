package org.example.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class TokenManager {

    private static String accessToken;
    private static long expiryTime = 0;

    public static String getAccessToken() {
        // 🔹 If token is still valid, return it
        if (accessToken != null && System.currentTimeMillis() < expiryTime) {
            return accessToken;
        }

        System.out.println("🔄 Fetching new token...");
        Response response = given()
                .baseUri("https://auth.example.com")
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", "your-client-id")
                .formParam("client_secret", "your-client-secret")
                .when()
                .post("/oauth2/token");

        // Extract token & expiry time
        accessToken = response.jsonPath().getString("access_token");
        int expiresIn = response.jsonPath().getInt("expires_in"); // in seconds
        expiryTime = System.currentTimeMillis() + (expiresIn * 1000) - 5000; // Buffer time

        System.out.println("✅ New Access Token: " + accessToken);
        return accessToken;
    }
}
