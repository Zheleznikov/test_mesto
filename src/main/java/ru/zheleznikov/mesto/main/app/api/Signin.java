package ru.zheleznikov.mesto.main.app.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Signin extends ApiBase {

    public Signin() throws IOException {
    }

    String getToken(String user) {
        Response res = reqPostSignin(user);
        return "Bearer " + res.then().extract().path("token");
    }

    Response reqPostSignin(String user) {
        return given().spec(reqSpec()).body(user).post("signin");
    }
}
