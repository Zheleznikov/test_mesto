package ru.zheleznikov.mesto.cases;

import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.JsonHelper.*;

public class Test_1_addCard extends TestBase {

    Card cardToAdd = new Card()
            .withName("Черный лебедь")
            .withLink("https://images.unsplash.com/photo-1576806112200-cb6902182bde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=314&q=80");

    @Test
    public void testAddCard_Api() {
        List<Card> cardsBefore = generateCardList(app.api().getCards());
        String body = generateStringToReq(cardToAdd);
        String cardToAdd_id = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardToAdd_id));
        List<Card> cardsAfter = generateCardList(app.api().getCards());
        assertThat(cardsBefore, equalTo(cardsAfter));
    }

    @Test
    public void testAddCard_Db() throws IOException {
        List<Card> cardsBefore = app.db().getCards();
        String body = generateStringToReq(cardToAdd);
        String cardToAdd_id = app.api().addCard(body);
        cardsBefore.add(cardToAdd.with_id(cardToAdd_id));
        List<Card> cardsAfter = app.db().getCards();

        assertThat(cardsBefore.size(), equalTo(cardsAfter.size()));
        assertThat(cardsBefore, equalTo(cardsAfter));
    }
}
