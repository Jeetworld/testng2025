package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiKeyExample {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5";

        Response response = given()
                .queryParam("q", "London")
                .queryParam("appid", "your_api_key")    //one way to add api key
                .header("x-api-key","your-api-key-value")   //another way to add api key
                .when()
                .get("/weather")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response: " + response.asString());
    }
}
