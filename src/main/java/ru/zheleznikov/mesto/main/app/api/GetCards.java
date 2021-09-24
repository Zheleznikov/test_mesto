package ru.zheleznikov.mesto.main.app.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.zheleznikov.mesto.main.modelhelpers.CardHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCards extends ApiBase {

    public GetCards() throws IOException {
    }

    public GetCards sendRq() {
        List<Object> cards = reqGetCards().then().extract().jsonPath().getList("data");
        handleRs = new CardHelper(cards);
        return this;
    }

    private Response reqGetCards() {
        return given().spec(reqSpec()).get(properties.getProperty("api.getCards"));
    }

}
