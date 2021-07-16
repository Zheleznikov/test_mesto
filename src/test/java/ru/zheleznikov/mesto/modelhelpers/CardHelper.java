package ru.zheleznikov.mesto.modelhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.app.ApiHelper;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.zheleznikov.mesto.utils.CommonHelper.getRandom;



public class CardHelper {

    public ApiHelper apiHelper;
    public List<Card> cards = new ArrayList<>();

    public List<Card> getCardList() {
        return cards;
    }

    public CardHelper() {

    }

    public CardHelper(ApiHelper apiHelper2) {
        apiHelper = apiHelper2;
    }

    public List<Card> generateCardList(List<Object> cardsFromApi) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(cardsFromApi);
        return gson.fromJson(json, new TypeToken<List<Card>>() {
        }.getType());// List<Card>.class
    }

    public CardHelper generateCardList() {
        List<Object> cardsFromApi = apiHelper.cards;
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(cardsFromApi);
         cards = gson.fromJson(json, new TypeToken<List<Card>>() {
        }.getType());// List<Card>.class
        return this;

    }

    public Card getRandomCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public List<Card> getExactUserCards(List<Card> cards, User user) {
        return cards.stream().filter(c -> c.getOwner().get_id().equals(user.get_id())).collect(Collectors.toList());
    }

    public Card getExactCard(List<Card> cards, String id) {
        return cards.stream().filter(c -> c.get_id().equals(id)).findFirst().get();
    }

    public List<Card> getOtherUsersCard(List<Card> cards, User user) {
        return cards.stream().filter(c -> !c.getOwner().get_id().equals(user.get_id())).collect(Collectors.toList());
    }

    public static Card getExactCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public Card getLastCard(List<Card> cards) {
        return cards.get(cards.size() - 1);
    }

    public Card getLastCard2() {
        return cards.get(cards.size() - 1);
    }

}
