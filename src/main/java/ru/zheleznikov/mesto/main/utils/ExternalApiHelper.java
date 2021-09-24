package ru.zheleznikov.mesto.main.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ExternalApiHelper {

    private static final String KEY = "N-rVWTHtk2N5e7FypCRkX5Vd7zO0jZX4gWjLGHQVX30";
    private static final String UNSPLASH_API_HOST = "https://api.unsplash.com/";
    private static final String DRY_CODES_API_HOST = "http://names.drycodes.com/";
    private static final String RESERVE_CARD_URL = "https://images.unsplash.com/photo-1572880393162-0518ac760495?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1267&q=80";

    public ExternalApiHelper() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader("src/main/resources/external.properties"));

    }

    public static String getRandomPhotoFromUnsplash() {
        try  {
            return given()
                    .baseUri(UNSPLASH_API_HOST)
                    .when()
                    .get("photos/random/?client_id=" + KEY)
                    .then().extract().path("urls.regular");
        }

        catch(Exception e)  {
            return RESERVE_CARD_URL;
        }


    }

    public static String getRandomNameFromDryCodes() {
         List<String> json = given()
                .baseUri(DRY_CODES_API_HOST + "3?nameOptions=funnyWords")
                .when()
                .get()
                .then()
                         .extract().jsonPath().getList("");
        return json.get(0);
    }

    public static List<String> getRandomNameFromDryCodes(int n) {
        return given()
                .baseUri(DRY_CODES_API_HOST + n + "?nameOptions=funnyWords")
                .when()
                .get()
                .then()
                .extract().jsonPath().getList("");
    }
}
