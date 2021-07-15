package ru.zheleznikov.mesto.cases;

import jdk.jfr.Description;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.*;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateCardList;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateStringToReq;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class Test_2_deleteCard extends TestBase {

    Card cardToAdd = new Card()
            .withName("Card to del")
            .withLink(getRandomPhotoFromUnsplash());

    @Test
    public void testDeleteCard_Api() {
        List<Card> cardsBefore = generateCardList(app.api().getCards());

        Card cardToDelete = getRandomCard(cardsBefore);
        cardsBefore.remove(cardToDelete);
        app.api().deleteCard(cardToDelete.get_id());

        List<Card> cardsAfter = generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    @Ignore
    public void testDeleteCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        Card cardToDelete = getRandomCard(cardsBefore);
        cardsBefore.remove(cardToDelete);
        app.api().deleteCard(cardToDelete.get_id());
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    @Description("Signed user trying to delete his card")
    public void testDeleteCard_Ui_cardBelongsToUser() {
        List<Card> cardsBefore = generateCardList(app.api().getCards());
        String _id = app.api().getCurrentUserId();
        List<Card> currentContactCards = getExactContactCards(cardsBefore, _id);
        Card cardToDelete = null;

        if (currentContactCards.size() == 0)
        {
            String body = generateStringToReq(cardToAdd);
            Map<String, String> cardData = app.api().addCard(body);
            cardToDelete = new Card().with_id(cardData.get("_id"));

        }
        else
        {
            cardToDelete = getRandomCard(currentContactCards);
        }

        app.ui().signin();
        app.ui().deleteExactCard(cardToDelete.get_id());
        app.ui().acceptAlert();

        cardsBefore.remove(cardToDelete);
        List<Card> cardsAfter = generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    @Description("Signed user trying to delete other card")
    public void testDeleteCard_Ui_cardDoesNotBelongToUser() {
        List<Card> cardsBefore = generateCardList(app.api().getCards());
        String _id = app.api().getCurrentUserId();
        List<Card> otherContactsCards = getOthersContactCard(cardsBefore, _id);

        Card cardToDelete = getRandomCard(otherContactsCards);

        app.ui().signin();
        app.ui().mouseOverCard(cardToDelete.get_id());
        app.ui().deleteExactCard(cardToDelete.get_id());

        List<Card> cardsAfter = generateCardList(app.api().getCards());

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void checkTheories() {
        Map<String,String> data = app.api().addCard(generateStringToReq(cardToAdd));
        System.out.println(data);
    }


}
