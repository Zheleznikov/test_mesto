package ru.zheleznikov.mesto.modelhelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.zheleznikov.mesto.model.Card;

import java.util.List;
import java.util.stream.Collectors;

import static ru.zheleznikov.mesto.utils.CommonHelper.getRandom;

public class CardHelper {

    public List<Card> generateCardList(List<Object> cardsFromApi) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(cardsFromApi);
        return gson.fromJson(json, new TypeToken<List<Card>>() {}.getType());// List<Card>.class
    }
    public  Card getRandomCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

    public  List<Card> getExactContactCards(List<Card> cards, String id) {
        return cards.stream().filter(c -> c.getOwner().get_id().equals(id)).collect(Collectors.toList());
    }

    public  Card getExactCard(List<Card> cards, String id) {
        return cards.stream().filter(c -> c.get_id().equals(id)).findFirst().get();
    }

    public  List<Card> getOthersContactCard(List<Card> cards, String id) {
        return cards.stream().filter(c -> !c.getOwner().get_id().equals(id)).collect(Collectors.toList());
    }

    public static Card getExactCard(List<Card> cards) {
        return cards.get(getRandom(cards.size()));
    }

}
