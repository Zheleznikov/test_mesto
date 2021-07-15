package ru.zheleznikov.mesto.app;

import io.restassured.response.Response;
import ru.zheleznikov.mesto.model.Signin;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> addCard(String cardData, String userData) {
        Response res = reqPostCard(cardData, userData);
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


}
