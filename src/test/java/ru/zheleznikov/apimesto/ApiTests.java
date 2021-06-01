package ru.zheleznikov.apimesto;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.zheleznikov.apimesto.Specification.reqSpec;

public class ApiTests extends BaseApiTests {


    @Test
    @Description("1. /signin - positive signing check")
    public void signinTestPositive() {
        given()
                .spec(reqSpec(correctRequestData))
                .when()
                .post(signin)
                .then()
                .log().all()
                .statusCode(200)
                .body("user.email", equalTo("cat@cat.cat"))
                .body("message", equalTo("ok"))
                .body("token", notNullValue());
    }

    @Test
    @Description("2. /signin - negative signing check - wrong email")
    public void signinTestNegativeWrongEmail() {
    }

    @Test
    @Description("3. /signin - negative signing check - wrong password")
    public void signinTestNegativeWrongPass() {
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
