package ru.zheleznikov.mesto.cases.bases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zheleznikov.mesto.main.app.legacy.ApplicationManager;

import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
//        model.init();
    }

    @AfterSuite
    public void tearDown() throws IOException {
        app.ui().close();
    }
}
