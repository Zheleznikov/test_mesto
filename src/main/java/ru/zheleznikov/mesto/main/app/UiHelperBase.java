package ru.zheleznikov.mesto.main.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UiHelperBase extends HelperBase {

    public WebDriver wd;
    private final String browser;
    protected final String EMAIL = properties.getProperty("email");
    protected final String PASSWORD = properties.getProperty("password");




    public UiHelperBase(String browser) throws IOException {
        this.browser = browser;
        init();
    }

    private void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        wd.get(properties.getProperty("host.ui"));
    }

    public void close() {
        wd.quit();
    }


    protected void waitUntilSpinnerStopped() {
        new WebDriverWait(wd, 5).until(ExpectedConditions.invisibilityOf(wd.findElement(By.cssSelector(".spinner_visible"))));
    }

    protected void refreshPage()  {
        wd.navigate().refresh();
//        Thread.sleep(500);
//        waitUntilSpinnerStopped();
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }


}