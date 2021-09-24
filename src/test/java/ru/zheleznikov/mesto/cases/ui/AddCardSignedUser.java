package ru.zheleznikov.mesto.cases.ui;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.main.app.UiHelper;
import ru.zheleznikov.mesto.main.model.Card;

import java.io.IOException;

import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class AddCardSignedUser {

    UiHelper ui = new UiHelper(BrowserType.CHROME);

    public AddCardSignedUser() throws IOException {
    }

    @Test
    public void test_addCardSignedUserViaUI() {

        // получить текущее количество

        Card card = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());

        ui.addCard(card);
        ui.signOut();

        // удалить карточку


    }
}
