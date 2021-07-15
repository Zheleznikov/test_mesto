package ru.zheleznikov.mesto.app;

import com.google.gson.Gson;
import io.restassured.response.Response;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.Signin;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;

public class ApiHelper extends ApiHelperBase {


    public ApiHelper() throws IOException {
    }

    private List<Object> processResponse(Response res) {
        return res.then()
                .statusCode(200)
                .extract().jsonPath().getList("data");
    }

    public List<Object> getCards() {
        return processResponse(reqGetCards());
    }


    public Map<String, String> addCard(String body) {
        Response res = reqPostCard(body);
        return res.then().extract().path("data");
    }

    public Map<String, String> addCard(Card card, User user) {
        Response res = reqPostCard(generateStringToReq(card), new Gson().toJson(user));
        return res.then().extract().path("data");
    }

    public void deleteCard(String id) {
        reqDeleteCardId(id).then().statusCode(200);
    }

    public String getCurrentUserId() {
        Response res;
        res = reqGetMyData();
        return res.then().extract().path("data._id");
    }


    public void signup(String body) {
        Response res = reqPostSignup(body);
        res.then().log().all();
    }

    public String getCurrentUserId(User user) {
        return reqPostSignin(new Gson().toJson(user)).then().extract().path("user._id");
    }


}
