package ru.zheleznikov.mesto.cases;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;

import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomNameFromDryCodes;

public class TestTheories extends TestBase {

    @Test
    public void signUp() throws IOException, InterruptedException {
        User currentUser = model.user().getUserFromJson();
        app.api().signup(currentUser);
    }
}
