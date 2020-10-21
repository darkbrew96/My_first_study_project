package ru.stormnet.yandex.pages.YandexDiscEnvironment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stormnet.yandex.pages.AbstractPage;
import ru.stormnet.yandex.utills.Expectant;

public class YandexDiscPage extends AbstractPage {

    private static final By FILE_TO_GENERAL_FOLDER_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[1]/div[1]/div/span");
    private static final By FILE_TO_TRASH_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[3]/div[1]/div/span");
    private static final By TRASH_LOCATOR = By.xpath("//*[@id=\"app\"]/div/div[4]/div[1]/div/div[3]/div/div[4]/div[1]/div/span");
    private static final By FOLDER_WITH_DOWNLOADS = By.cssSelector(".navigation__items.navigation__items_favorite");
    private static final By GENERAL_FOLDER_LOCATOR = By.cssSelector("#\\/disk");
    private static final By MOVING_FILE_PROGRESSBAR_LOCATOR = By.className(".b-progressbar__fill");


    public YandexDiscPage(WebDriver driver) {
        super(driver);
    }

    private void dragAndDropFile(By fromElementLocator, By toElementLocator) {
        new Expectant(driver).waitingForAnElementOnThePage(fromElementLocator);
        new Expectant(driver).waitingForAnElementOnThePage(toElementLocator);
        WebElement fromElement = driver.findElement(fromElementLocator);
        WebElement toElement = driver.findElement(toElementLocator);
        Actions action = new Actions(driver);
        action.dragAndDrop(fromElement, toElement).build().perform();
    }

    public void openFolderWithDownloads() {
        new Expectant(driver).waitingForAnElementOnThePage(FOLDER_WITH_DOWNLOADS);
        driver.findElement(FOLDER_WITH_DOWNLOADS).click();
        logger.info("Folder with downloads is opened!");
    }

    public void dragAndDropFileToGeneralFolder() {
        dragAndDropFile(FILE_TO_GENERAL_FOLDER_LOCATOR, GENERAL_FOLDER_LOCATOR);
    }

    public void openGeneralFolder() {
        driver.findElement(GENERAL_FOLDER_LOCATOR).click();
        logger.info("General folder is opened!");
    }

    public void dragAndDropFileToTrash() {
        dragAndDropFile(FILE_TO_TRASH_LOCATOR, TRASH_LOCATOR);
    }

    public void refreshPage() {
        driver.navigate().refresh();
        logger.info("page is refreshed!");
    }

    public boolean fileIsMovedToGeneralFolder() {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(MOVING_FILE_PROGRESSBAR_LOCATOR));
            logger.info("File is moved to general folder!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Failed to move file to main folder!***");
            return false;
        }
    }

    public boolean fileIsMovedToTrash() {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(MOVING_FILE_PROGRESSBAR_LOCATOR));
            logger.info("File is moved to trash!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Failed to move file to trash***");
            return false;
        }
    }
}
