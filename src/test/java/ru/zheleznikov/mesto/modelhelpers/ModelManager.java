package ru.zheleznikov.mesto.modelhelpers;

import org.testng.mustache.Model;
import ru.zheleznikov.mesto.app.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelManager {
    public ApiHelper apiHelper;
    private CardHelper cardHelper;
    private UserHelper userHelper;

    public ModelManager() {

    }

    public ModelManager(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
        this.cardHelper = new CardHelper(this.apiHelper);
    }

    public void init() {
        cardHelper = new CardHelper(apiHelper);
        userHelper = new UserHelper();
    }

    public CardHelper card() {
        return cardHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
