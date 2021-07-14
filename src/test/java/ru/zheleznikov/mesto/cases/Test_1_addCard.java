package ru.zheleznikov.mesto.cases;

import org.junit.jupiter.api.Disabled;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateCardList;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_1_addCard extends TestBase {

    Card cardToAdd = new Card()
            .withName(getRandomName())
            .withLink(getRandomPhotoFromUnsplash());

    public Test_1_addCard() throws IOException {
    }

    @Test
    public void testAddCard_Api() {
        List<Card> cardsBefore = generateCardList(app.api().getCards());
        String body = generateStringToReq(cardToAdd);
        String cardToAdd_id = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardToAdd_id));
        List<Card> cardsAfter = generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


    @Test
    @Ignore
    public void testAddCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        String body = generateStringToReq(cardToAdd);
        String cardToAdd_id = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardToAdd_id));
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_signUser() throws IOException, InterruptedException {
        List<Card> cardsBefore = generateCardList(app.api().getCards());

        app.ui().signin();
        app.ui().addCard();

        Card addedCard = app.ui().getLastCard();
        cardsBefore.add(addedCard);

        List<Card> cardsAfter = generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_unsignUser() throws IOException, InterruptedException {
        List<Card> cardsBefore = generateCardList(app.api().getCards());
        app.ui().addCard();
        List<Card> cardsAfter = generateCardList(app.api().getCards());
        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


}
