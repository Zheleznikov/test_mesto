package ru.zheleznikov.mesto.cases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zheleznikov.mesto.app.ApplicationManager;

import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

//    @AfterSuite
    public void tearDown() {
        app.ui().close();
    }
}
