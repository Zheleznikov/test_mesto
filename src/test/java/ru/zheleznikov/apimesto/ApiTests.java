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
import static ru.zheleznikov.utils.CommonUtils.getSigninJson;

import org.json.*;
import ru.zheleznikov.jsonClasses.Signin;

public class ApiTests extends BaseApiTests {

    @Test
    @Description("1. /signin - positive signing check")
    public void signinTestPositive() throws IOException {
        Signin apiSignin = getSigninJson();

        given()
                .spec(reqSpec(apiSignin))
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
                .log().all();
    }

    @Test
    public void getCards() {
        given()
                .spec(reqSpec(""))
                .when()
                .get("cards")
                .then()
                .log().headers();
    }


}
