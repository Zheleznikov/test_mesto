package ru.zheleznikov.mesto.main.modelhelpers;

import ru.zheleznikov.mesto.main.app.api.GetCards;

public class ModelManager {
    private final CardHelper cardHelper;
    private UserHelper userHelper;

    public ModelManager() {
        cardHelper = new CardHelper();
        userHelper = new UserHelper();
    }


    public ModelManager(GetCards getCards) {
        cardHelper = new CardHelper();
    }


    public CardHelper card() {
        return cardHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
