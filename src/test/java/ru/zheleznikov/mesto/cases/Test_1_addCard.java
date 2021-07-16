package ru.zheleznikov.mesto.cases;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;
import ru.zheleznikov.mesto.modelhelpers.CardHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_1_addCard extends TestBase {



    @Test
    public void testAddCard_Ui_signedUser() {
        User currentUser = model.user().getUserFromJson();
        Card cardToAdd = new Card().withName(getRandomNameFromDryCodes()).withLink(getRandomPhotoFromUnsplash());

        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getCardList();

        app.ui().signIn(currentUser);
        app.ui().addCard(cardToAdd);
        app.ui().signOut();

        CardHelper cards = app.api().getCards().model.card().generateCardList();
        List<Card> cardsListAfter = cards.getCardList();
        Card addedCard = cards.getLastCard();

        cardListBefore.add(addedCard);

        assertThat(cardListBefore.size(), equalTo(cardsListAfter.size()));
        assertThat(cardListBefore, equalTo(cardsListAfter));
    }

    @Test
    public void testAddCard_Ui_unsignedUser() {
        Card cardToAdd = new Card().withName(getRandomNameFromDryCodes()).withLink(getRandomPhotoFromUnsplash());

        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getCardList();

        app.ui().addCard(cardToAdd);

        List<Card> cardsListAfter = app.api().getCards().model.card().generateCardList().getCardList();

        assertThat(cardListBefore.size(), equalTo(cardsListAfter.size()));
        assertThat(cardListBefore, equalTo(cardsListAfter));
    }

    @Test
    public void testAddCard_Api() {
        User currentUser = model.user().getUserFromJson().preparedForSignIn();
        Card cardToAdd = new Card().withName(getRandomNameFromDryCodes()).withLink(getRandomPhotoFromUnsplash());

        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getCardList();
        Map<String, String> addedCardData = app.api().addCard(cardToAdd, currentUser);
//        cardListBefore.add(cardToAdd.with_id(addedCardData.get("_id")));
//
//        List<Card> cardListAfter = app.api().getCards().model.card().generateCardList().getCardList();
//
//        assertThat(cardListBefore.size(), equalTo(cardListAfter.size()));
//        assertThat(cardListBefore, equalTo(cardListAfter));
    }

    @Test
    @Ignore
    public void testAddCard_Db() throws IOException {
        Card cardToAdd = new Card().withName(getRandomName()).withLink(getRandomPhotoFromUnsplash());
        List<Card> cardsBefore = app.db().getCards();

        String body = generateStringToReq(cardToAdd);
        Map<String, String> cardData = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardData.get("_id")));

        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


}
