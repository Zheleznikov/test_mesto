package ru.zheleznikov.mesto.main.app.api;

import com.google.gson.Gson;
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
        String preparedUser = processUser(user);
        String preparedCard = processCard(card);
        Object cardAsObj = reqPostCard(preparedCard, preparedUser)
                .then().extract().jsonPath().getJsonObject("data");
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


    private String processUser(User user) {
        User userUpdated = new User().setPassword(properties.getProperty("default.pass")).setEmail(user.getEmail());
        return new Gson().toJson(userUpdated);
    }

    private String processCard(Card card) {
        return generateStringToReq(card);
    }



}
