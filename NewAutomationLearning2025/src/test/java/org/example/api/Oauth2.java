package org.example.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Oauth2 {

    @Test
    public void handlingAuth2(){
        String accessToken = given()
                .formParam("client_id", "your-client-id")
                .formParam("client_secret", "your-client-secret")
                .formParam("grant_type", "client_credentials")
                .when()
                .post("https://auth.mastercard.com/oauth/token")
                .then()
                .extract().path("access_token");

        given()
                .auth().oauth2(accessToken)
                .when().get("/api/transactions")
                .then().statusCode(200);

    }
}
