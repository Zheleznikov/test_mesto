package ru.zheleznikov.apimesto;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests extends BaseApiTests {


    @Test
    public void signinTest() {
        Response response = given()
                .contentType("application/json")
                .body(correctRequestData)
                .when()
                .post(apiHost + signin)
                .then()
                .log().body()
                .extract()
                .response();


        String token = "Bearer " + response.jsonPath().get("token");
        System.out.println(token);
        String email = response.jsonPath().get("user.email");
        System.out.println(email);

        // get info about me

//        given()
//                .contentType("application/json")
//                .header("Authorization", token)
//                .when()
//                .get(apiHost + "me")
//                .then()
//                .log().all();

    }


}
