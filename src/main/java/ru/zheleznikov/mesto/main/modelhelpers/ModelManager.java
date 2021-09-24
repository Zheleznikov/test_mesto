package ru.zheleznikov.mesto.main.modelhelpers;

import ru.zheleznikov.mesto.main.app.ApiHelper;

public class ModelManager {
    private final CardHelper cardHelper;
    private UserHelper userHelper;

    public ModelManager() {
        cardHelper = new CardHelper();
        userHelper = new UserHelper();
    }

    public ModelManager(ApiHelper apiHelper) {
        cardHelper = new CardHelper(apiHelper);
    }


    public CardHelper card() {
        return cardHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
