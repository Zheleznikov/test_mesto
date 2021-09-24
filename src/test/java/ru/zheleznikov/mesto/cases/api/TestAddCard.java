package ru.zheleznikov.mesto.cases.api;

import org.testng.annotations.Test;
import ru.zheleznikov.mesto.main.app.api.AddCard;
import ru.zheleznikov.mesto.main.app.api.DeleteCard;
import ru.zheleznikov.mesto.main.app.api.GetCards;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;
import ru.zheleznikov.mesto.main.modelhelpers.UserHelper;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.main.utils.ExternalApiHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.main.utils.ExternalApiHelper.getRandomPhotoFromUnsplash;

public class TestAddCard {

    UserHelper userHelper = new UserHelper();
    GetCards getCards = new GetCards();
    AddCard addCard = new AddCard();
    DeleteCard deleteCard = new DeleteCard();


    public TestAddCard() throws IOException {
    }

    @Test
    public void test_AddCard() {
        User user = userHelper.getUserFromJson();
        List<Card> cardsBefore = getCards.sendRq().handleRs.asModelList();
        Card cardToAdd = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());
        Card cardUpdated = addCard.sendRq(cardToAdd, user).handleRs.asModel();
        List<Card> cardsAfter = getCards.sendRq().handleRs.asModelList();
        assertThat(cardsBefore.size() + 1, equalTo(cardsAfter.size()));
        cardsBefore.add(cardUpdated);
        assertThat(cardsBefore, equalTo(cardsAfter));
        deleteCard.sendRq(cardUpdated, user).handleRs.asModel();
    }

}
