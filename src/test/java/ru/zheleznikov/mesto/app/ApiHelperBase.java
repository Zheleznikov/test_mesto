package ru.zheleznikov.mesto.app;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.zheleznikov.mesto.model.Signin;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static ru.zheleznikov.mesto.legacy.Specification.reqSpec;

public class ApiHelperBase extends HelperBase {

    private final String HOST = properties.getProperty("host.api");
    private final String EMAIL = properties.getProperty("email");
    private final String PASSWORD = properties.getProperty("password");
    private final Signin SIGNIN = new Signin().withEmail(EMAIL).withPassword(PASSWORD);


    public ApiHelperBase() throws IOException {
    }

    protected Response reqGetCards() {
        return given()
                .spec(reqSpec())
                .when()
                .get("cards");
    }

    protected Response reqPostSignin() {
        return given()
                .spec(reqSpec())
                .body(SIGNIN)
                .when()
                .post("signin");
    }

    protected Response reqPostCard(String body) {
        return given()
                .spec(reqSpec())
                .header("authorization", getToken())
                .body(body)
                .when()
                .post("cards");
    }

    protected Response reqDeleteCardId(String id) {
        return given()
                .spec(reqSpec())
                .header("authorization", getToken())
                .when()
                .delete("cards/" + id);


    }

    private String getToken() {
        Response res = reqPostSignin();
        return "Bearer " + res
                .then()
                .extract()
                .path("token");
    }

    private RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(HOST)
                .setContentType(ContentType.JSON)
                .build();
    }
}
