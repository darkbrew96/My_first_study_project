package ru.stormnet.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stormnet.yandex.utills.Expectant;

public class LoginPage extends AbstractPage {

    Expectant expectant = new Expectant(driver);

    private static final By USERNAME_FIELD_LOCATOR = By.cssSelector("#passp-field-login");
    private static final By LOGIN_BUTTON_LOCATOR = By.cssSelector("div.passp-sign-in-button.v5 > button");
    private static final By PASSWORD_FIELD_LOCATOR = By.cssSelector("#passp-field-passwd");

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    public WebElement getLoginField() {
        return driver.findElement(USERNAME_FIELD_LOCATOR);
    }

    public WebElement getPasswordField() {
        return driver.findElement(PASSWORD_FIELD_LOCATOR);
    }

    public WebElement getButtonLocator() {
        return driver.findElement(LOGIN_BUTTON_LOCATOR);
    }

    public void loginWithCreeds(String username, String password) {//вернуть страницу профиля
        expectant.waitingForAnElementOnThePage(USERNAME_FIELD_LOCATOR);
        getLoginField().sendKeys(username);
        getButtonLocator().click();
        expectant.waitingForAnElementOnThePage(PASSWORD_FIELD_LOCATOR);
        getPasswordField().sendKeys(password);
        expectant.waitingForAnElementOnThePage(LOGIN_BUTTON_LOCATOR);
        getButtonLocator().click();
    }

}

