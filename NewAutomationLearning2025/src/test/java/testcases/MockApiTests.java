package testcases;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MockApiTests {
    public static void main(String[] args) {
        // Start WireMock on port 8080
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        // Define a mock response
        wireMockServer.stubFor(get(urlEqualTo("/api/users/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":1, \"name\":\"John Doe\", \"email\":\"john@example.com\"}")
                )
        );

        // Test with RestAssured
        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/api/users/1")
                .then()
                .statusCode(200)
                .body("name", (Matcher<?>) equalTo("John Doe"))
                .body("email", (ResponseAwareMatcher<Response>) equalTo("john@example.com"));

        // Stop WireMock server
        wireMockServer.stop();
    }
}

