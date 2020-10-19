package ru.stormnet.yandex.utills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Expectant {

    protected WebDriver driver;

    public Expectant(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingForAnElementOnThePage(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void waitingForTheMissingElementOnThePage(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }
}
