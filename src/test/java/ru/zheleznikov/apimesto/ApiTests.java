package ru.zheleznikov.apimesto;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    String apiHost = "https://api-mesto.zheleznikov.ru/";

    @Test
    public void signinTest() {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("email", "polwen@yandex.ru");
        requestData.put("password", "1234qwerty");

        Response response = given()
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(apiHost + "signin")
                .then()
//                .log().body()
                .extract()
                .response();

        String token = "Bearer " + response.jsonPath().get("token");
        System.out.println(token);

        // get info about me

        given()
                .contentType("application/json")
                .header("Authorization", token)
                .when()
                .get(apiHost + "me")
                .then()
                .log().all();


    }


}
