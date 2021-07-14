package ru.zheleznikov.mesto.cases;

import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomCard;
import static ru.zheleznikov.mesto.utils.JsonHelper.generateCardList;

public class Test_2_deleteCard extends TestBase {

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
    public void testDeleteCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        Card cardToDelete = getRandomCard(cardsBefore);
        cardsBefore.remove(cardToDelete);
        app.api().deleteCard(cardToDelete.get_id());
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }


}
