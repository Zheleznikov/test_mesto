package ru.zheleznikov.mesto.cases;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;

import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomNameFromDryCodes;

public class TestTheories extends TestBase {

    @Test
    public void generateUser() throws IOException, InterruptedException {
        List<Card> cards = app.api().getCards2().model.card().generateCardList();
        cards.forEach(System.out::println);
//
//        List<Object> cards2 = app.api().getCards2().model.card().cards;
//        cards2.forEach(System.out::println);

    }
}
