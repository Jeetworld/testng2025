package org.example.api;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.base.BaseTest;
import org.example.pojos.User;
import org.example.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import java.util.Map;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;


public class WayToHandleResponse {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public void setupSuite() {
        System.out.println("Starting Test Suite...");
        extent = ExtentReportManager.getInstance();
        test = ExtentReportManager.createTest("Before Suite Code Executed");
        test.info("Executing before suite");
        test.pass("Executing before suite...");
    }

    @Test
    public void handleSimpleAsString(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");
        String jsonResponse = response.asString();
        System.out.println("JSON Response as String: " + jsonResponse);
        test.info(jsonResponse);

    }

    @Test
    public void handleJsonPath(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");

        JsonPath jsonPath = response.jsonPath();
        String userName = jsonPath.getString("username");
        System.out.println("Extracted Username: " + userName);
    }

    @Test
    public void handleWithPOJO(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");
        response.getStatusCode();
        User user = response.as(User.class);
        System.out.println("User Name: " + user.getName());
        System.out.println("User Email: " + user.getEmail());
    }


    @Test
    public void handleJsonAsMap(){
        Response response = (Response) RestAssured
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(2000L));

        Map<String, Object> jsonMap = response.jsonPath().getMap("$");
        System.out.println("Extracted Map: " + jsonMap);
    }

    @Test
    public void handleAsJsonArray(){
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users");

        JsonPath jsonPath = response.jsonPath();
        List<String> usernames = jsonPath.getList("username");

        System.out.println("Usernames in JSON Array: " + usernames);
    }

    @Test
    public void validateAPIResponse() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/users/1");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.asString().contains("Leanne Graham"));
    }

    @Test
    public void handleSimpleAsStrings(){
        Response response = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/users/1");
        String jsonResponse = response.asString();
        System.out.println("JSON Response as String: " + jsonResponse);
    }
}
