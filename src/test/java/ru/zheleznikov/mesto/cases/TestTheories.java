package ru.zheleznikov.mesto.cases;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.util.List;

public class TestTheories extends TestBase {

    @Test
    public void generateUser() throws IOException, InterruptedException {
        app.ui().signIn();
        app.ui().signOut();


    }
}
