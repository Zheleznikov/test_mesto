package ru.zheleznikov.mesto.cases.bases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zheleznikov.mesto.main.app.legacy.ApplicationManager;
import ru.zheleznikov.mesto.main.modelhelpers.ModelManager;

import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();
    protected static ModelManager model = new ModelManager();

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
