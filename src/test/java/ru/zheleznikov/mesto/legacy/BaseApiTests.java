package ru.zheleznikov.mesto.legacy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.zheleznikov.mesto.legacy.Specification.reqSpec;

public class BaseApiTests {
    // apiHost
    public final static String apiHost = "https://api-mesto.zheleznikov.ru/";
    protected final static String signin = "signin";
    protected final static String me = "me";

    // signin Data
    protected final static Map<String, String> correctRequestData = new HashMap<>();
    protected final static Map<String, String> incorrectDataEmail = new HashMap<>();
    protected final static Map<String, String> incorrectDataPass = new HashMap<>();

    protected final static String emailKey = "email";
    protected final static String passKey = "password";

    protected final static String correctEmail = "cat@cat.cat";
    protected final static String correctPass = "1234qwerty";
    protected final static String incorrectPass = "1234qwert";
    protected final static String emptyField = "";
    protected final static String incorrectEmail = "mail@";


    static {
        correctRequestData.put(emailKey, correctEmail);
        correctRequestData.put(passKey, correctPass);
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
