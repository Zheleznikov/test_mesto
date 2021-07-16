package ru.zheleznikov.mesto.app;

import com.google.gson.Gson;
import io.restassured.response.Response;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;
import ru.zheleznikov.mesto.modelhelpers.ModelManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;

public class ApiHelper extends ApiHelperBase {

    public ModelManager model;
    public List<Object> cards = new ArrayList<>();

    public ApiHelper() throws IOException {

    }


    private List<Object> processResponse(Response res) {
        return res.then()
                .statusCode(200)
                .extract().jsonPath().getList("data");
    }

    public ApiHelper getCards() {
        cards = processResponse(reqGetCards());
        model = new ModelManager(this);
        return this;
    }


    public Map<String, String> addCard(String body) {
        Response res = reqPostCard(body);
        return res.then().extract().path("data");
    }

    public Map<String, String> addCard(Card card, User user) {
        Response res = reqPostCard(generateStringToReq(card), new Gson().toJson(user));
        return res.then().log().body().extract().path("data");
    }

    public void deleteCard(String id) {
        reqDeleteCardId(id).then().statusCode(200);
    }

    public String getCurrentUserId() {
        Response res;
        res = reqGetMyData();
        return res.then().extract().path("data._id");
    }


    public void signup(User user) {
        String body = new Gson().toJson(user);
        System.out.println(body);
        Response res = reqPostSignup(body);
        res.then().log().all();
    }

    public String getCurrentUserId(User user) {
        return reqPostSignin(new Gson().toJson(user)).then().extract().path("user._id");
    }


}
