package ru.zheleznikov.mesto.main.modelhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.zheleznikov.mesto.main.utils.CommonHelper.getRandom;
import static ru.zheleznikov.mesto.main.utils.JsonHelper.generateCard;
import static ru.zheleznikov.mesto.main.utils.JsonHelper.generateCardList;


public class CardHelper {

    private Object cardToHandle;
    private List<Card> cards = new ArrayList<>();
    private List<Object> listOfCardsToHandle = new ArrayList<>();


    public CardHelper() {

    }


    public CardHelper(List<Object> listOfCardsToHandle) {
        this.listOfCardsToHandle = listOfCardsToHandle;
    }

    public CardHelper(Object cardToHandle) {
        this.cardToHandle = cardToHandle;
    }


    public List<Card> asModelList() {
        return generateCardList(listOfCardsToHandle);
    }

    public Card asModel() {
        return generateCard(cardToHandle);
    }

    public List<Card> getList() {
        return cards;
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

    public Card getLastCard() {
        return cards.get(cards.size() - 1);
    }

}
