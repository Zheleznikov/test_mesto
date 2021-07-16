package ru.zheleznikov.mesto.cases;

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
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_2_deleteCard extends TestBase {

    Card cardToAdd = new Card()
            .withName("Card to del")
            .withLink(getRandomPhotoFromUnsplash());


    @Test
    @Description("Signed user trying to delete his card")
    public void testDeleteCard_Ui_cardBelongsToUser() {
        User currentUser = model.user().getUserFromJson().preparedForSignIn();
        currentUser.with_id(app.api().getCurrentUserId(currentUser));

        List<Card> cardsBefore = app.api().getCards().model.card().generateCardList().getCardList();
        List<Card> currentUserCards = model.card().getExactUserCards(cardsBefore, currentUser);

        Card cardToDelete = null;

        if (currentUserCards.size() == 0) {
            Map<String, String> cardData = app.api().addCard(cardToAdd, currentUser);
            cardToDelete = new Card().with_id(cardData.get("_id"));
        } else {
            cardToDelete = model.card().getRandomCard(currentUserCards);
        }

        app.ui().signIn(currentUser);
        app.ui().deleteExactCard(cardToDelete);
        app.ui().acceptAlert();
        app.ui().signOut();

        cardsBefore.remove(cardToDelete);

        List<Card> cardsAfter = app.api().getCards().model.card().generateCardList().getCardList();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    @Description("Signed user trying to delete other card")
    public void testDeleteCard_Ui_cardDoesNotBelongToUser() {
        User currentUser = model.user().getUserFromJson().preparedForSignIn();
        currentUser.with_id(app.api().getCurrentUserId(currentUser));

        List<Card> cardsBefore = app.api().getCards().model.card().generateCardList().getCardList();
        List<Card> otherContactsCards = model.card().getOtherUsersCard(cardsBefore, currentUser);

        Card cardToDelete = null;
        if (otherContactsCards.size() == 0) {
            System.out.println("oops");
            User helperUser = model.user().getUserFromJson(1);
            // create new user through API
            // add card by this user
            //
        } else {
            cardToDelete = model.card().getRandomCard(otherContactsCards);
        }


        app.ui().signIn();
        app.ui().mouseOverCard(cardToDelete);
        app.ui().deleteExactCard(cardToDelete);
        app.ui().signOut();

        List<Card> cardsAfter = app.api().getCards().model.card().generateCardList().getCardList();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }



    @Test
    @Description("Unsigned user trying to delete any card")
    public void testDeleteCard_Ui_unsignedUser() {
        List<Card> cardsBefore = app.api().getCards().model.card().generateCardList().getCardList();

        Card cardToDelete = model.card().getRandomCard(cardsBefore);

        app.ui().mouseOverCard(cardToDelete);
        app.ui().deleteExactCard(cardToDelete);

        List<Card> cardsAfter = app.api().getCards().model.card().generateCardList().getCardList();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    // case won't work if random card doesn't belong to current user
    @Test
    @Ignore
    public void testDeleteCard_Api() {
        List<Card> cardListBefore = app.api().getCards().model.card().generateCardList().getCardList();

        Card cardToDelete = model.card().getRandomCard(cardListBefore);
        cardListBefore.remove(cardToDelete);
        app.api().deleteCard(cardToDelete.get_id());

        List<Card> cardsAfter = app.api().getCards().model.card().generateCardList().getCardList();

        assertThat(cardListBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardListBefore, equalTo(cardsAfter));
    }

    @Test
    @Ignore
    public void testDeleteCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        Card cardToDelete = model.card().getRandomCard(cardsBefore);
        cardsBefore.remove(cardToDelete);
        app.api().deleteCard(cardToDelete.get_id());
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


}
