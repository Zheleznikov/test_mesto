package ru.zheleznikov.mesto.utils;

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
}
