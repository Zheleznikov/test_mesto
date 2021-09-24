package ru.zheleznikov.mesto.cases;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.main.app.UiHelper;
import ru.zheleznikov.mesto.main.model.User;
import ru.zheleznikov.mesto.main.modelhelpers.ModelManager;

import java.io.IOException;

public class AddCardSignedUserViaUI {

    UiHelper ui = new UiHelper(BrowserType.CHROME);
    ModelManager model = new ModelManager();

    public AddCardSignedUserViaUI() throws IOException {
    }

    @Test
    public void test_addCardSignedUserViaUI() {
        User user = model.user().getUserFromJson();
    }
}
