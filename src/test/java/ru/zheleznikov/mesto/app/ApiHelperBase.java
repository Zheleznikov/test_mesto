package ru.zheleznikov.mesto.app;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ApiHelperBase extends HelperBase {

    private final String HOST = properties.getProperty("host.api");
    private final String EMAIL = properties.getProperty("email");
    private final String PASSWORD = properties.getProperty("password");
    private final User USER = new User().withEmail(EMAIL).withPassword(PASSWORD);


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
                .body(new Gson().toJson(USER))
                .when()
                .post("signin");
    }

    protected Response reqPostSignin(String user) {

        return given()
                .spec(reqSpec())
                .body(user)
                .when()
                .post("signin");
    }

    public Response reqPostSignin2(User user) {
        return given()
                .spec(reqSpec())
                .body(user)
                .when()
                .post("signin");
    }

    private String getToken(String user) {
        Response res = reqPostSignin(user);
        return "Bearer " + res
                .then()
                .extract()
                .path("token");
    }

    protected Response reqPostCard(String body) {
        return given()
                .spec(reqSpec())
                .header("authorization", getToken())
                .body(body)
                .when()
                .post("cards");
    }

    protected Response reqPostCard(String cardData, String userData) {
        return given()
                .spec(reqSpec())
                .header("authorization", getToken(userData))
                .body(cardData)
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

    protected Response reqGetMyData() {
        return given()
                .spec(reqSpec())
                .header("authorization", getToken())
                .when()
                .get("me");

    }

    protected Response reqPostSignup(String body) {
        return given()
                .spec(reqSpec())
                .body(body)
                .when()
                .post("signup");
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
