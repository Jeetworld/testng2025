package org.example.api;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MockAPITest {
    private WireMockServer wireMockServer;

    @BeforeClass
    public void setupMockServer() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        stubFor(get(urlEqualTo("/api/mock-user"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 1, \"name\": \"John Doe\" }")));
    }

    @Test
    public void testMockedUserAPI() {
        Response response = RestAssured.get("http://localhost:8080/api/mock-user");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "John Doe");
    }

    @AfterClass
    public void teardown() {
        wireMockServer.stop();
    }


    public Response retryRequestWithBackoff(String endpoint, int maxRetries) {      //handling the 429 error
        int retryCount = 0;
        int waitTime = 1000; // Start with 1 second

        while (retryCount < maxRetries) {
            Response response = RestAssured.get(endpoint);

            if (response.statusCode() == 200) {
                return response;
            } else if (response.statusCode() == 429) {
                retryCount++;
                System.out.println("Rate Limit Exceeded. Retrying in " + waitTime + "ms...");
                try { Thread.sleep(waitTime); } catch (InterruptedException ignored) {}
                waitTime *= 2; // Double the wait time
            } else {
                break;
            }
        }
        return null;
    }

}
