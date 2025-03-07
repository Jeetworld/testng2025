package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class APIUtils {

    public static Response sendGetRequest(String endpoint, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        if (headers != null) {
            request.headers(headers);
        }
        return request.get(endpoint);
    }

    public static Response sendPostRequest(String endpoint, String body, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(body);
        return request.post(endpoint);
    }

    public static Response sendPutRequest(String endpoint, String body, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(body);
        return request.put(endpoint);
    }

    public static Response sendDeleteRequest(String endpoint, Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        return request.delete(endpoint);
    }
}
