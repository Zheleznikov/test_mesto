package ru.zheleznikov.mesto.modelhelpers;

public class ModelManager {
    private CardHelper cardHelper;
    private UserHelper userHelper;


    public void init() {
        cardHelper = new CardHelper();
        userHelper = new UserHelper();
    }

    public CardHelper card() {
        return cardHelper;
    }

    public UserHelper user() {
        return userHelper;
    }
}
