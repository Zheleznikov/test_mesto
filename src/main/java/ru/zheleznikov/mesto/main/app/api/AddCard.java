package ru.zheleznikov.mesto.main.app.api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;
import ru.zheleznikov.mesto.main.modelhelpers.CardHelper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static ru.zheleznikov.mesto.main.utils.JsonHelper.generateStringToReq;

public class AddCard extends ApiBase {

    public Signin signin = new Signin();


    public AddCard() throws IOException {

    }

    public AddCard sendRq(Card card, User user) {
        User user2 = new User().setPassword(properties.getProperty("default.pass")).setEmail(user.getEmail());
        Response res = reqPostCard(generateStringToReq(card), new Gson().toJson(user2));
        Object cardAsObj = res.then().extract().jsonPath().getJsonObject("data");
        handleRs = new CardHelper(cardAsObj);
        return this;
    }

    private Response reqPostCard(String cardData, String userData) {
        return given()
                .spec(reqSpec())
                .header("authorization", signin.getToken(userData))
                .body(cardData)
                .post(properties.getProperty("api.addCard"));
    }


}
