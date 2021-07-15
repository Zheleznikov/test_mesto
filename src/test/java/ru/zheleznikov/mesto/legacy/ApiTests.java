package ru.zheleznikov.mesto.legacy;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static ru.zheleznikov.mesto.legacy.LegacyHelper.getSigninJson;
import static ru.zheleznikov.mesto.legacy.LegacyHelper.getSigninJson2;

public class ApiTests {

    // apiHost
    public final static String apiHost = "https://api-mesto.zheleznikov.ru/";
    protected final static String signin = "signin";

    // signin Data
    protected final static Map<String, String> correctRequestData = new HashMap<>();

    protected final static String emailKey = "email";
    protected final static String passKey = "password";

    protected final static String correctEmail = "cat@cat.cat";
    protected final static String correctPass = "1234qwerty";


    static {
        correctRequestData.put(emailKey, correctEmail);
        correctRequestData.put(passKey, correctPass);
    }

//    @Test
//    @Description("1. /signin - positive signing check")
    public void signinTestPositive() throws IOException {
        User apiSignin = getSigninJson();
        String apiSignin2 = getSigninJson2();

        given()
                .spec(reqSpec(apiSignin2))
                .when()
                .post(signin)
                .then()
                .log().all()
                .statusCode(200);
//                .body("user.email", equalTo(apiSignin.getEmail()))
//                .body("message", equalTo("ok"))
//                .body("token", notNullValue());
    }

    public static RequestSpecification reqSpec(Object reqData) {
        return new RequestSpecBuilder()
                .setBaseUri(apiHost)
                .setContentType(ContentType.JSON)
                .setBody(reqData)
                .build();
    }

    protected static String getToken() {
        return "Bearer " + given()
                .spec(reqSpec(correctRequestData))
                .when()
                .post(signin)
                .then()
                .extract()
                .path("token");
    }






}
