package ru.zheleznikov.mesto.app;

import io.restassured.response.Response;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;

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

    public String addCard(String body) {
        Response res = reqPostCard(body);
        return res.then().statusCode(200).extract().path("data._id");
    }


    public void deleteCard(String id) {
        reqDeleteCardId(id).then().statusCode(200);
    }

    public String getCurrentUserId() {
        Response res = reqGetMyData();
        return res.then().extract().path("data._id");
    }


}
