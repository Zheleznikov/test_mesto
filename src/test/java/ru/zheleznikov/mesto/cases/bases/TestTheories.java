package ru.zheleznikov.mesto.cases.bases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.main.app.UiHelper;
import ru.zheleznikov.mesto.main.model.Card;
import ru.zheleznikov.mesto.main.model.User;
import ru.zheleznikov.mesto.main.modelhelpers.ModelManager;

import java.io.IOException;

import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomNameFromDryCodes;
import static ru.zheleznikov.mesto.main.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class TestTheories  {

    UiHelper ui = new UiHelper(BrowserType.CHROME);
    ModelManager model = new ModelManager();

    public TestTheories() throws IOException {
    }


    @Test
    public void signUp()  {
        User user = model.user().getUserFromJson();
        Card card = new Card().setName(getRandomNameFromDryCodes()).setLink(getRandomPhotoFromUnsplash());
        ui.signIn(user);
        ui.addCard(card);
        ui.signOut();
    }
}
