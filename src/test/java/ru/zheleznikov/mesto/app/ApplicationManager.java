package ru.zheleznikov.mesto.app;

import org.openqa.selenium.remote.BrowserType;
import ru.zheleznikov.mesto.modelhelpers.CardHelper;
import ru.zheleznikov.mesto.modelhelpers.UserHelper;

import java.io.IOException;

public class ApplicationManager {

    private UserHelper userHelper;
    private ApiHelper apiHelper;
    private MongoHelper mongoHelper;
    private UiHelper uiHelper;
    private CardHelper cardHelper;




    public void init() throws IOException {
        apiHelper = new ApiHelper();
        mongoHelper = new MongoHelper();
        uiHelper = new UiHelper(BrowserType.CHROME);
        cardHelper = new CardHelper();
        userHelper = new UserHelper();
    }

    public ApiHelper api() {
        return this.apiHelper;
    }

    public MongoHelper db() {
        return this.mongoHelper;
    }

    public UiHelper ui() {
        return this.uiHelper;
    }

    public CardHelper card() {
        return this.cardHelper;
    }

    public UserHelper user() {
        return userHelper;
    }


}
