package org.example.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class HandleResponseTime {

    @Test
    public void handleResponseTime(){
        RestAssured.given()
                .when().get("/api/payments")
                .then()
                .time(lessThan(2000L)); // Ensures response is under 2 seconds

    }
}
