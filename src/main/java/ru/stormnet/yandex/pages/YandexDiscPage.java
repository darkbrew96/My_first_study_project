package ru.stormnet.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.stormnet.yandex.utills.Expectant;

public class YandexDiscPage extends AbstractPage {

    public static final By FILE_TO_GENERAL_FOLDER_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[1]/div[1]/div/span");
    public static final By FILE_TO_TRASH_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[3]/div[1]/div/span");
    public static final By TRASH_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[4]/div[1]/div/span");
    public static final By FOLDER_WITH_DOWNLOADS = By.cssSelector(".navigation__items.navigation__items_favorite");
    public static final By GENERAL_FOLDER_LOCATOR = By.cssSelector("#\\/disk");


    public YandexDiscPage(WebDriver driver) {
        super(driver);
    }

    public void dragAndDropFile(By fromElementLocator, By toElementLocator) {
        new Expectant(driver).waitingForAnElementOnThePage(fromElementLocator);
        new Expectant(driver).waitingForAnElementOnThePage(toElementLocator);
        WebElement fromElement = driver.findElement(fromElementLocator);
        WebElement toElement = driver.findElement(toElementLocator);
        Actions action = new Actions(driver);
        action.dragAndDrop(fromElement, toElement).build().perform();
    }

    public void openFolderWithDownloads () {
        new Expectant(driver).waitingForAnElementOnThePage(FOLDER_WITH_DOWNLOADS);
        driver.findElement(FOLDER_WITH_DOWNLOADS).click();
    }

    public void dragAndDropFileToGeneralFolder() {
        dragAndDropFile(FILE_TO_GENERAL_FOLDER_LOCATOR,GENERAL_FOLDER_LOCATOR);
    }

    public void openGeneralFolder() {
        driver.findElement(GENERAL_FOLDER_LOCATOR).click();
    }

    public void dragAndDropFileToTrash () {
        dragAndDropFile(FILE_TO_TRASH_LOCATOR,TRASH_LOCATOR);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }







}
