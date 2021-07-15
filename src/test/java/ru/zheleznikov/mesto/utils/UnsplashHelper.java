package ru.zheleznikov.mesto.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UnsplashHelper {
    private static final String KEY = "N-rVWTHtk2N5e7FypCRkX5Vd7zO0jZX4gWjLGHQVX30";
    private static final String UNSPLASH_API_HOST = "https://api.unsplash.com/";

    public static String getRandomPhotoFromUnsplash() {
        return given()
                .baseUri(UNSPLASH_API_HOST)
                .when()
                .get("photos/random/?client_id=" + KEY)
                .then().extract().path("urls.regular");

    }

    public static String getRandomNameFromDryCodes() {
         List<String> json = given()
                .baseUri("http://names.drycodes.com/3?nameOptions=funnyWords")
                .when()
                .get()
                .then()
                         .extract().jsonPath().getList("");
        return json.get(0);
    }

    public static List<String> getRandomNameFromDryCodes(int n) {
        return given()
                .baseUri("http://names.drycodes.com/" + n + "?nameOptions=funnyWords")
                .when()
                .get()
                .then()
                .extract().jsonPath().getList("");
    }
}
