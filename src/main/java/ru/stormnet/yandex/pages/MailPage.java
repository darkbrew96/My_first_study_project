package ru.stormnet.yandex.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import ru.stormnet.yandex.utills.Expectant;
import ru.stormnet.yandex.utills.PropertiesManager;

import java.util.Set;

public class MailPage extends AbstractPage {


    private static final Logger logger = LogManager.getLogger(MailPage.class);

    private static final By MAIL_PAGE_LOCATOR = By.cssSelector("a.yandex-header__logo-service.count-me");
    private static final By FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR = By.cssSelector(".tst-field-to .composeYabbles");
    private static final By FILE_INPUT_LOCATOR = By.cssSelector("div.ComposeAttachFileButton > input");
    private static final By LETTER_POP_UP_LOCATOR = By.cssSelector("div.ns-view-compose-buttons-box");
    private static final By SEND_MESSAGE_LOCATOR = By.cssSelector("div.ComposeSendButton_desktop > button");
    public static final By UFO_BUTTON_LOCATOR = By.cssSelector(".js-show-save-popup > svg");
    public static final By OPEN_DISC_BUTTON_LOCATOR = By.xpath("//*[@id=\"app\"]//div/a");
    public static final By CIRCLE_OF_SUCCESSFUL_DOWNLOADS_LOCATOR = By.cssSelector("#app > div > div > svg > path");
    public static final By DISC_WIDGET_SAVE_LOCATOR = By.className("disk-widget-save");

    public MailPage(WebDriver driver) {
        super(driver);

    }


    public void openLetterPop_Up() {
        new Expectant(driver).waitingForAnElementOnThePage(LETTER_POP_UP_LOCATOR);
        driver.findElement(LETTER_POP_UP_LOCATOR).click();

    }

    public WebElement getAddress() {
        return driver.findElement(FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR);
    }

    public void enterAddress() {
        new Expectant(driver).waitingForAnElementOnThePage(FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR);
        getAddress().sendKeys(PropertiesManager.getProperty("file_recipient_address"));
    }


    public void attachFileToMessage() {
        new Expectant(driver).waitingForAnElementOnThePage(FILE_INPUT_LOCATOR);
        WebElement fileInput = driver.findElement(FILE_INPUT_LOCATOR);
        fileInput.sendKeys(PropertiesManager.getProperty("path_to_the_file"));
    }

    public void sendMessageToRecipient() {
        new Expectant(driver).waitingForAnElementOnThePage(SEND_MESSAGE_LOCATOR);
        driver.findElement(SEND_MESSAGE_LOCATOR).click();
    }

    public void saveFileToYandexDisc() {
        new Expectant(driver).waitingForAnElementOnThePage(UFO_BUTTON_LOCATOR);
        driver.findElement(UFO_BUTTON_LOCATOR).click();

    }

    public void goToYandexDiscPage() throws InterruptedException {
        Set<String> handle1 = driver.getWindowHandles();
        Thread.sleep(3000);
        driver.switchTo().frame(1);

        new Expectant(driver).waitingForAnElementOnThePage(OPEN_DISC_BUTTON_LOCATOR);
        driver.findElement(OPEN_DISC_BUTTON_LOCATOR).click();
        Set<String> handle2 = driver.getWindowHandles();
        handle2.removeAll(handle1);
        Object[] array = handle2.toArray();
        driver.switchTo().window((String) array[0]);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }




    public boolean isOpened() {
        try {
            new Expectant(driver).waitingForAnElementOnThePage(MAIL_PAGE_LOCATOR);
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean fileSavedToYandexDisc() {
        driver.switchTo().frame(1);
        try {
            new Expectant(driver).waitingForAnElementOnThePage(CIRCLE_OF_SUCCESSFUL_DOWNLOADS_LOCATOR);
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
