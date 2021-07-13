package ru.zheleznikov.mesto.utils;

import ru.zheleznikov.mesto.model.Card;

import java.util.List;

public class CommonHelper {

    public static Card getRandomCard(List<Card> cards) {
        return cards.get((int) ( Math.random() * cards.size()));
    }
}
