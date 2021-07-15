package ru.zheleznikov.mesto.cases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.zheleznikov.mesto.app.ApplicationManager;
import ru.zheleznikov.mesto.modelhelpers.ModelManager;

import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();
    protected static final ModelManager model = new ModelManager();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        model.init();
    }

    @AfterSuite
    public void tearDown() {
        app.ui().close();
    }
}
