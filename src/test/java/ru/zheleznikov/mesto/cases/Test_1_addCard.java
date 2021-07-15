package ru.zheleznikov.mesto.cases;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.Signin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_1_addCard extends TestBase {

    Card cardToAdd = new Card()
            .withName(getRandomName())
            .withLink(getRandomPhotoFromUnsplash());

    final Signin signin = new Signin().withEmail("cat@cat2.cat").withPassword("1234qwerty");

    public Test_1_addCard() throws IOException {
    }

    @Test
    @Description("")
    public void testAddCard_Api() {
        List<Card> cardsBefore = app.card().generateCardList(app.api().getCards());

        String body = generateStringToReq(cardToAdd);

        Map<String, String> cardData = app.api().addCard(body, signin);
        cardsBefore.add(cardToAdd.with_id(cardData.get("_id")));

        List<Card> cardsAfter = app.card().generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


    @Test
    @Ignore
    public void testAddCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        String body = generateStringToReq(cardToAdd);
        Map<String, String> cardData = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardData.get("_id")));
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_signUser() throws IOException, InterruptedException {
        List<Card> cardsBefore = app.card().generateCardList(app.api().getCards());

        app.ui().signin();
        app.ui().addCard();

        Card addedCard = app.ui().getLastCard();
        cardsBefore.add(addedCard);

        List<Card> cardsAfter = app.card().generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_unsignUser() throws IOException, InterruptedException {
        List<Card> cardsBefore = app.card().generateCardList(app.api().getCards());
        app.ui().addCard();
        List<Card> cardsAfter = app.card().generateCardList(app.api().getCards());
        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


}
