package ru.zheleznikov.mesto.cases;

import com.google.gson.Gson;
import jdk.jfr.Description;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_1_addCard extends TestBase {

    @Test
    public void testAddCard_Api() {
        User currentUser = model.user().getUserFromJson().withoutName().withoutAbout().withoutAvatar();
        List<Card> cardsListBefore = model.card().generateCardList(app.api().getCards());

        Card cardToAdd = new Card().withName(getRandomName()).withLink(getRandomPhotoFromUnsplash());
        Map<String, String> cardResData = app.api()
                .addCard(generateStringToReq(cardToAdd), new Gson().toJson(currentUser));

        cardsListBefore.add(cardToAdd.with_id(cardResData.get("_id")));
        List<Card> cardsAfter = model.card().generateCardList(app.api().getCards());

        assertThat(cardsListBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsListBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_signedUser() {
        User currentUser = model.user().getUserFromJson();
        Card cardToAdd = new Card().withName(getRandomName()).withLink(getRandomPhotoFromUnsplash());

        List<Card> cardListBefore = model.card().generateCardList(app.api().getCards());

        app.ui().signIn(currentUser);
        app.ui().addCard(cardToAdd);
        app.ui().signOut();

        Card addedCard = model.card().getLastCard(model.card().generateCardList(app.api().getCards()));
        cardListBefore.add(addedCard);

        List<Card> cardsAfter = model.card().generateCardList(app.api().getCards());

        assertThat(cardListBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardListBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Ui_unsignedUser() {
        List<Card> cardsBefore = model.card().generateCardList(app.api().getCards());
        Card cardToAdd = new Card().withName(getRandomName()).withLink(getRandomPhotoFromUnsplash());

        app.ui().addCard(cardToAdd);
        List<Card> cardsAfter = model.card().generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
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
