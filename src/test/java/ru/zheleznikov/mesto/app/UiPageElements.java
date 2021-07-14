package ru.zheleznikov.mesto.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class UiPageElements extends UiHelperBase {
    public final WebElement addCardButton = wd.findElement(By.cssSelector(".user-info__button"));
    public final WebElement cardNameInput = wd.findElement(By.xpath("//div[contains(@class, 'popup-add-card')]//input[@name='name']"));
    public final WebElement cardLinkInput = wd.findElement(By.xpath("//div[contains(@class, 'popup-add-card')]//input[@name='link']"));
    public final WebElement submitAddCardButton = wd.findElement(By.cssSelector(".popup-add-card__button"));
    public final WebElement signinButton = wd.findElement(By.cssSelector(".header__button_entry"));
    public final WebElement signinUserEmailInput = wd.findElement(By.xpath("//form[@name='entry']//input[@type='email']"));
    public final WebElement signinUserPasswordInput = wd.findElement(By.xpath("//form[@name='entry']//input[@type='password']"));
    public final WebElement submitUserSigninButton = wd.findElement(By.cssSelector(".popup-entry__button"));




    public UiPageElements(String browser) throws IOException {
        super(browser);


//


    }
}
