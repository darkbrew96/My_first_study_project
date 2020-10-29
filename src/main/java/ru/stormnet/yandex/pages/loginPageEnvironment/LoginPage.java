package ru.stormnet.yandex.pages.loginPageEnvironment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stormnet.yandex.pages.AbstractPage;
import ru.stormnet.yandex.utills.Expectant;

public class LoginPage extends AbstractPage {

    private static final By USERNAME_FIELD_LOCATOR = By.cssSelector("#passp-field-login");
    private static final By LOGIN_BUTTON_LOCATOR = By.cssSelector("div.passp-sign-in-button > button");
    private static final By PASSWORD_FIELD_LOCATOR = By.cssSelector("#passp-field-passwd");

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    private WebElement getLoginField() {
        return driver.findElement(USERNAME_FIELD_LOCATOR);
    }

    private WebElement getPasswordField() {
        return driver.findElement(PASSWORD_FIELD_LOCATOR);
    }

    private WebElement getButtonLocator() {
        new Expectant(driver).waitingForAnElementOnThePage(LOGIN_BUTTON_LOCATOR);
        return driver.findElement(LOGIN_BUTTON_LOCATOR);
    }

    public void loginWithCreeds(String username, String password) {
        new Expectant(driver).waitingForAnElementOnThePage(USERNAME_FIELD_LOCATOR);
        getLoginField().sendKeys(username);
        getButtonLocator().click();
        logger.info("username entered");
        new Expectant(driver).waitingForAnElementOnThePage(PASSWORD_FIELD_LOCATOR);
        getPasswordField().sendKeys(password);
        logger.info("password entered");
        new Expectant(driver).waitingForAnElementOnThePage(LOGIN_BUTTON_LOCATOR);
        getButtonLocator().click();
    }

}

