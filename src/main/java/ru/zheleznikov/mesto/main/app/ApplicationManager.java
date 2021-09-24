package ru.zheleznikov.mesto.main.app;

import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

public class ApplicationManager {

    private ApiHelper apiHelper;
    private MongoHelper mongoHelper;
    private UiHelper uiHelper;


    public void init() throws IOException {
        apiHelper = new ApiHelper();
        mongoHelper = new MongoHelper();
    }

    public ApiHelper api() {
        return this.apiHelper;
    }

    public MongoHelper db() {
        return this.mongoHelper;
    }

    public UiHelper ui() throws IOException {
        uiHelper = new UiHelper(BrowserType.CHROME);
        return this.uiHelper;
    }


}
