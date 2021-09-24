package ru.zheleznikov.mesto.cases.bases;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.main.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.main.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_1_addCard extends TestBase {



    @Test
    public void testAddCard_Ui_signedUser() throws IOException {
        User currentUser = model.user().getUserFromJson();
        Card cardToAdd = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());

//        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getCardList();
//        System.out.println(cardListBefore.size());

        app.ui().signIn(currentUser);
        app.ui().addCard(cardToAdd);
//        app.ui().signOut();
//
//        CardHelper cards = app.api().getCards().model.card().generateCardList();
//        List<Card> cardsListAfter = cards.getCardList();
//        Card addedCard = cards.getLastCard();
//
//        cardListBefore.add(addedCard);
//
//        assertThat(cardListBefore.size(), equalTo(cardsListAfter.size()));
//        assertThat(cardListBefore, equalTo(cardsListAfter));
    }

//    @Test
//    public void testAddCard_Ui_unsignedUser() throws IOException {
//        Card cardToAdd = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());
//
//        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getList();
//
//        app.ui().addCard(cardToAdd);
//
//        List<Card> cardsListAfter = app.api().getCards().model.card().generateCardList().getList();
//
//        assertThat(cardListBefore.size(), equalTo(cardsListAfter.size()));
//        assertThat(cardListBefore, equalTo(cardsListAfter));
//    }
//
//    @Test
//    public void testAddCard_Api() {
//        User currentUser = model.user().getUserFromJson().preparedForSignIn();
//        Card cardToAdd = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());
//
//        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getList();
//        Map<String, String> addedCardData = app.api().addCard(cardToAdd, currentUser);
//        cardListBefore.add(cardToAdd.set_id(addedCardData.get("_id")));
//
//        List<Card> cardListAfter = app.api().getCards().model.card().generateCardList().getList();
//
//        assertThat(cardListBefore.size(), equalTo(cardListAfter.size()));
//        assertThat(cardListBefore, equalTo(cardListAfter));
//    }
//
//    @Test
//    @Ignore
//    public void testAddCard_Db() throws IOException {
//        Card cardToAdd = new Card().setName(getRandomName()).setLink(getRandomPhotoFromUnsplash());
//        List<Card> cardsBefore = app.db().getCards();
//
//        String body = generateStringToReq(cardToAdd);
//        Map<String, String> cardData = app.api().addCard(body);
//        cardsBefore.add(cardToAdd.set_id(cardData.get("_id")));
//
//        List<Card> cardsAfter = app.db().getCards();
//
//        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
//        assertThat(cardsBefore, equalTo(cardsAfter));
//    }


}
