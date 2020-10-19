package ru.stormnet.yandex.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stormnet.yandex.utills.Expectant;


import java.util.Set;

import static org.openqa.selenium.By.cssSelector;

public class SearchPage extends AbstractPage {

    Expectant expectant = new Expectant(driver);


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {//возвратить логинпэйдж
        Set<String> handle1 = driver.getWindowHandles();
        expectant.waitingForAnElementOnThePage(cssSelector(".desk-notif-card__card a.button"));
        WebElement loginButton = driver.findElement(cssSelector(".desk-notif-card__card a.button"));
        loginButton.click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
        logger.info("go to login page");
    }
}

