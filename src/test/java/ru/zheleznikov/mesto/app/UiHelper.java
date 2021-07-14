package ru.zheleznikov.mesto.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.zheleznikov.mesto.model.Card;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static ru.zheleznikov.mesto.utils.CommonHelper.getRandomName;
import static ru.zheleznikov.mesto.utils.UnsplashHelper.getRandomPhotoFromUnsplash;

public class UiHelper extends UiPageElements {

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

    public void signin() {
        signinButton.click();
        signinUserEmailInput.sendKeys(EMAIL);
        signinUserPasswordInput.sendKeys(PASSWORD);
        submitUserSigninButton.click();
        waitUntilSpinnerStopped();
    }

    public List<Card> getCards() {
        List<WebElement> cardsWebElements = wd.findElements(By.xpath("//div[@name='card']"));
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
//        WebElement lastCard =
//                wd.findElement(By.xpath("//div[@name='card']"));
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
        acceptAlert();
    }


}
