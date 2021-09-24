package ru.zheleznikov.mesto.main.app.legacy;

import org.openqa.selenium.remote.BrowserType;
import ru.zheleznikov.mesto.main.app.UiHelper;
import ru.zheleznikov.mesto.main.app.db.MongoHelper;

import java.io.IOException;

public class ApplicationManager {

    private MongoHelper mongoHelper;
    private UiHelper uiHelper;


    public void init() throws IOException {
        mongoHelper = new MongoHelper();
    }


    public MongoHelper db() {
        return this.mongoHelper;
    }

    public UiHelper ui() throws IOException {
        uiHelper = new UiHelper(BrowserType.CHROME);
        return this.uiHelper;
    }


}
