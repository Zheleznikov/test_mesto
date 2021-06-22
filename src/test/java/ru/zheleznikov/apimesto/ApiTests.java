package ru.zheleznikov.apimesto;

import groovy.json.JsonOutput;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.zheleznikov.apimesto.Specification.reqSpec;
import static ru.zheleznikov.utils.CommonUtils.*;

import org.json.*;
import ru.zheleznikov.jsonClasses.Signin;

public class ApiTests extends BaseApiTests {

    @Test
    @Description("1. /signin - positive signing check")
    public void signinTestPositive() throws IOException {
        Signin apiSignin = getSigninJson();
        String apiSignin2 = getSigninJson2();

        given()
                .spec(reqSpec(apiSignin2))
                .when()
                .post(signin)
                .then()
                .log().all()
                .statusCode(200)
                .body("user.email", equalTo(apiSignin.getEmail()))
                .body("message", equalTo("ok"))
                .body("token", notNullValue());
    }

    @Test
    public void getMyData() {
        String token = getToken();
        given()
                .spec(reqSpec(""))
                .header("authorization", token)
                .when()
                .get(me)
                .then()
                .log().body();
    }

    @Test
    public void getCards() {
        String id = given()
                .spec(reqSpec(""))
                .when()
                .get("cards")
                .then()
//                .log().body()
                .extract().path("data.owner").toString();
        System.out.println(id);
    }

    @Test
    public void postCard() throws IOException {
        String token = getToken();
        String postCard = postCardJson();
        given()
                .spec(reqSpec(postCard))
                .header("authorization", token)
                .when()
                .post("cards")
                .then()
                .log().all();
    }

    @Test
    public void deleteCard() {
        String token = getToken();
        String id = "60b7b9b1ec264003d4e93321";

        given()
                .spec(reqSpec(""))
                .header("authorization", token)
                .when()
                .delete("cards/" + id)
                .then()
                .log().all();

    }

}
