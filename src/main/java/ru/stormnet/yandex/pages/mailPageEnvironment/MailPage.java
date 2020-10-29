package ru.stormnet.yandex.pages.mailPageEnvironment;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stormnet.yandex.pages.AbstractPage;
import ru.stormnet.yandex.utills.Expectant;

import java.util.Set;

public class MailPage extends AbstractPage {


    private static final Logger logger = LogManager.getLogger(MailPage.class);

    private static final By MAIL_PAGE_LOCATOR = By.cssSelector("a.yandex-header__logo-service.count-me");
    private static final By LETTER_POP_UP_LOCATOR = By.cssSelector("div.ns-view-compose-buttons-box");
    private static final By UFO_BUTTON_LOCATOR = By.cssSelector(".js-show-save-popup > svg");
    private static final By OPEN_DISC_BUTTON_LOCATOR = By.xpath("//*[@id=\"app\"]//div/a");
    private static final By CIRCLE_OF_SUCCESSFUL_DOWNLOADS_LOCATOR = By.cssSelector("#app > div > div > svg > path");
    private static final By DISC_WIDGET_SAVE_LOCATOR = By.className("disk-widget-save");


    public MailPage(WebDriver driver) {
        super(driver);

    }


    public void openLetterPop_Up() {
        new Expectant(driver).waitingForAnElementOnThePage(LETTER_POP_UP_LOCATOR);
        driver.findElement(LETTER_POP_UP_LOCATOR).click();

    }

    public void saveFileToYandexDisc() {
        new Expectant(driver).waitingForAnElementOnThePage(UFO_BUTTON_LOCATOR);
        driver.findElement(UFO_BUTTON_LOCATOR).click();

    }

    public void goToYandexDiscPage() {
        Set<String> handle1 = driver.getWindowHandles();
        new Expectant(driver).waitingFrameAndSwitchToIt(DISC_WIDGET_SAVE_LOCATOR);
        Assert.assertTrue(fileSavedToYandexDisc(), "***Failed of saved file to Yandex Disc***");
        new Expectant(driver).waitingForAnElementOnThePage(OPEN_DISC_BUTTON_LOCATOR);
        driver.findElement(OPEN_DISC_BUTTON_LOCATOR).click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    public void refreshPage() {
        driver.navigate().refresh();
        logger.info("page is refreshed!");
    }


    public boolean isOpened() {
        try {
            new Expectant(driver).waitingForAnElementOnThePage(MAIL_PAGE_LOCATOR);
            logger.info("mail page is opened!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Unsuccessful page opening***");
            return false;
        }
    }

    public boolean fileSavedToYandexDisc() {
        try {
            new Expectant(driver).waitingForAnElementOnThePage(CIRCLE_OF_SUCCESSFUL_DOWNLOADS_LOCATOR);
            logger.info("file saved to Yandex Disc");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Failed of saved file to Yandex Disc!***");
            return false;
        }

    }
}
