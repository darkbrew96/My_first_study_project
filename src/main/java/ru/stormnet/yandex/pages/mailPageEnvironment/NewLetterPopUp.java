package ru.stormnet.yandex.pages.mailPageEnvironment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stormnet.yandex.pages.AbstractPage;
import ru.stormnet.yandex.utills.Expectant;
import ru.stormnet.yandex.utills.PropertiesManager;

import java.io.File;

public class NewLetterPopUp extends MailPage {

    private static final By FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR = By.cssSelector(".tst-field-to .composeYabbles");
    private static final By FILE_INPUT_LOCATOR = By.cssSelector(" div.composeReact__inner > div.composeReact__footer > div > div:nth-child(2) > div:nth-child(1) > input");
    private static final By LOADING_BAR_LOCATOR = By.cssSelector("div.ComposeAttachmentsLoadingProgress");
    private static final By SEND_MESSAGE_BUTTON_LOCATOR = By.cssSelector("div.ComposeSendButton_desktop > button");

    public NewLetterPopUp(WebDriver driver) {
        super(driver);
    }


    private WebElement getAddress() {
        new Expectant(driver).waitingForAnElementOnThePage(FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR);
        return driver.findElement(FILE_RECIPIENT_ADDRESS_FIELD_LOCATOR);
    }

    public void enterAddress() {
        getAddress().sendKeys(PropertiesManager.getProperty("file_recipient_address"));
        AbstractPage.logger.info("address is entered!");

    }

    public void attachFileToMessage(File fileToSend) {
        WebElement fileInput = driver.findElement(FILE_INPUT_LOCATOR);
        fileInput.sendKeys(fileToSend.getAbsolutePath());
    }

    public void sendMessageToRecipient() {
        new Expectant(driver).waitingForAnElementOnThePage(SEND_MESSAGE_BUTTON_LOCATOR);
        driver.findElement(SEND_MESSAGE_BUTTON_LOCATOR).click();
        AbstractPage.logger.info("message sent to recipient!");
    }

    public boolean allFileIsAttached() {
        try {
            new Expectant(driver).waitingForTheMissingElementOnThePage(LOADING_BAR_LOCATOR);
            AbstractPage.logger.info("all file is attached!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            AbstractPage.logger.info("***Failed to file attachments***");
            return false;
        }
    }
}


