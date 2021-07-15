package ru.zheleznikov.mesto.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.zheleznikov.mesto.model.Card;
import ru.zheleznikov.mesto.model.User;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class UiHelper extends UiHelperBase {
    public final WebElement addCardButton = wd.findElement(By.cssSelector(".user-info__button"));
    public final WebElement cardNameInput = wd.findElement(By.xpath("//div[contains(@class, 'popup-add-card')]//input[@name='name']"));
    public final WebElement cardLinkInput = wd.findElement(By.xpath("//div[contains(@class, 'popup-add-card')]//input[@name='link']"));
    public final WebElement submitAddCardButton = wd.findElement(By.cssSelector(".popup-add-card__button"));
    public final WebElement signinButton = wd.findElement(By.cssSelector(".header__button_entry"));
    public final WebElement signinUserEmailInput = wd.findElement(By.xpath("//form[@name='entry']//input[@type='email']"));
    public final WebElement signinUserPasswordInput = wd.findElement(By.xpath("//form[@name='entry']//input[@type='password']"));
    public final WebElement submitUserSigninButton = wd.findElement(By.cssSelector(".popup-entry__button"));
    public final List<WebElement> cardsWebElements = wd.findElements(By.xpath("//div[@name='card']"));
    public final WebElement signOutButton = wd.findElement(By.cssSelector(".header__button"));
    public final WebElement signOutButton1 = new WebDriverWait(wd, 3)
            .until(driver -> driver.findElement(By.cssSelector(".header__button_exit")));

    public UiHelper(String browser) throws IOException {
        super(browser);
    }

    public void addCard() throws IOException, InterruptedException {
//        waitUntilSpinnerStopped();
        addCardButton.click();
        cardNameInput.sendKeys(getRandomName());
        cardLinkInput.sendKeys(getRandomPhotoFromUnsplash());
        submitAddCardButton.click();
        waitUntilSpinnerStopped();
        refreshPage();
    }

    public void addCard(Card card)  {
//        waitUntilSpinnerStopped();
        addCardButton.click();
        cardNameInput.sendKeys(card.getName());
        cardLinkInput.sendKeys(card.getLink());
        submitAddCardButton.click();
        waitUntilSpinnerStopped();
        refreshPage();
    }

    public void signIn() {
        signinButton.click();
        signinUserEmailInput.sendKeys(EMAIL);
        signinUserPasswordInput.sendKeys(PASSWORD);
        submitUserSigninButton.click();
        waitUntilSpinnerStopped();
    }

    public void signIn(User user) {
        signinButton.click();
        signinUserEmailInput.sendKeys(user.getEmail());
        signinUserPasswordInput.sendKeys(user.getPassword());
        submitUserSigninButton.click();
        waitUntilSpinnerStopped();
    }

    public void signOut() {
        try {
            wd.findElement(By.cssSelector(".header__button_exit")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Card> getCards() {
//        List<WebElement> cardsWebElements = wd.findElements(By.xpath("//div[@name='card']"));
        List<Card> listOfCards = new ArrayList<>();

        cardsWebElements.forEach(card -> {
            String id = card.getAttribute("id");
            String link = card.findElement(By.xpath("./div[contains(@class, 'place-card__image')]")).getCssValue("background-image");
            String sub = link.substring(5, link.length() - 2);
            String name = card.findElement(By.xpath(".//h3")).getText();
            listOfCards.add(
                    new Card()
                            .withName(name)
                            .with_id(id)
                            .withLink(sub)
            );

        });
        return listOfCards;
    }

    public Card getLastCard() {
        WebElement lastCard = new WebDriverWait(wd, 3)
                .until(driver -> driver.findElement(By.xpath("//div[@name='card']")));
        String id = lastCard.getAttribute("id");
        String link = lastCard.findElement(By.xpath("./div[contains(@class, 'place-card__image')]")).getCssValue("background-image");
        String sub = link.substring(5, link.length() - 2);
        String name = lastCard.findElement(By.xpath(".//h3")).getText();
        return new Card()
                .withName(name)
                .with_id(id)
                .withLink(sub);
    }

    public void deleteExactCard(String id) {
        WebElement card = wd.findElement(By.id(id));
        card.findElement(By.xpath(".//button[contains(@class, 'place-card__delete-icon')]")).click();
//        acceptAlert();
    }

    public void mouseOverCard(String id) {
        Actions actions = new Actions(wd);
        WebElement card = wd.findElement(By.id(id));
        actions.moveToElement(card).build().perform();
    }



}
