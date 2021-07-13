package ru.zheleznikov.mesto.cases;

import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomLink;
import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.JsonHelper.*;
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
    public void testim() throws IOException {
        String link =
                given()
                        .baseUri("https://api.unsplash.com/")
                        .when()
                        .get("photos/random/?client_id=N-rVWTHtk2N5e7FypCRkX5Vd7zO0jZX4gWjLGHQVX30")
                        .then().extract().path("urls.regular");
    }


}
