package ru.zheleznikov.mesto.main.app.api;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;
import ru.zheleznikov.mesto.main.modelhelpers.CardHelper;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeleteCard extends ApiBase {
    Signin signin = new Signin();

    public DeleteCard() throws IOException {
    }

    public DeleteCard sendRq(Card card, User user) {
        String preparedUser = processUser(user);
        String preparedCard = card.get_id();
        Object cardAsObj = reqDeleteCardId(preparedCard, preparedUser)
                .then().extract().jsonPath().getJsonObject("removedCard");
        handleRs = new CardHelper(cardAsObj);
        return this;
    }

    private String processUser(User user) {
        User updatedUser = new User().setPassword(properties.getProperty("default.pass")).setEmail(user.getEmail());
        return new Gson().toJson(updatedUser);
    }

    private Response reqDeleteCardId(String id, String user) {
        return given()
                .spec(reqSpec())
                .header("authorization", signin.getToken(user))
                .delete(properties.getProperty("api.deleteCard") + id);
    }
}
