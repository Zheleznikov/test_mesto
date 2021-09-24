package ru.zheleznikov.mesto.cases.bases;

import org.openqa.selenium.remote.BrowserType;
import ru.zheleznikov.mesto.main.app.UiHelper;

import java.io.IOException;

import static ru.zheleznikov.mesto.main.utils.ExternalApiHelper.getRandomNameFromDryCodes;

public class TestTheories  {

    UiHelper ui = new UiHelper(BrowserType.CHROME);

    public TestTheories() throws IOException {
    }



}
