package ru.stormnet.yandex.pages.YandexDiscEnvironment;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.stormnet.yandex.pages.AbstractPage;
import ru.stormnet.yandex.utills.Expectant;

import java.io.File;

public class YandexDiscPage extends AbstractPage {

    private static final By FILE_TO_GENERAL_FOLDER_LOCATOR = By.xpath("//*[@id=\"app\"]//div[4]/div[1]//div[3]/div/div[1]/div[1]");
    private static final By YANDEX_DISC_PAGE_LOCATOR = By.cssSelector(".burger-sidebar");
    private static final By TRASH_LOCATOR = By.xpath("//span[contains(@class, 'file-icon_dir_trash') or contains(@class, 'file-icon_dir_trash-full')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]");
    private static final By FOLDER_WITH_DOWNLOADS = By.cssSelector(".navigation__items.navigation__items_favorite");
    private static final By GENERAL_FOLDER_LOCATOR = By.cssSelector("#\\/disk");
    private static final By MESSAGE_ABOUT_SUCCESSFUL_MOVE_LOCATOR = By.cssSelector(".ns-view-id-3 > div");
    private static final By MOVING_FILE_PROGRESSBAR_LOCATOR = By.className(".b-progressbar__fill");
    private static final String DOWNLOAD_FILE_TEMPLATE = "//span[contains(@title, '%s')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]";

    public YandexDiscPage(WebDriver driver) {
        super(driver);
    }

    private By getDownloadedFileButton(File fileToSend) {
        String xpathOfDownloadFile = String.format(DOWNLOAD_FILE_TEMPLATE, fileToSend.getName());
        By DOWNLOAD_FILE_LOCATOR = By.xpath(xpathOfDownloadFile);
        new Expectant(driver).waitingForAnElementOnThePage(DOWNLOAD_FILE_LOCATOR);
        return DOWNLOAD_FILE_LOCATOR;

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
        logger.info("folder with downloads is opened!");
    }

    public void dragAndDropFileToGeneralFolder() {
        dragAndDropFile(FILE_TO_GENERAL_FOLDER_LOCATOR, GENERAL_FOLDER_LOCATOR);
    }

    public void openGeneralFolder() {
        driver.findElement(GENERAL_FOLDER_LOCATOR).click();
        logger.info("general folder is opened!");
    }

    public void dragAndDropFileToTrash(File fileToSend) {
        dragAndDropFile(getDownloadedFileButton(fileToSend), TRASH_LOCATOR);
    }

    public void refreshPage() {
        driver.navigate().refresh();
        logger.info("page is refreshed!");
    }

    public boolean isOpened() {
        try {
            new Expectant(driver).waitingForAnElementOnThePage(YANDEX_DISC_PAGE_LOCATOR);
            logger.info("Yandex Disc page is opened!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Unsuccessful Yandex Disc page opening***");
            return false;
        }
    }

    public boolean fileIsMovedToGeneralFolder() {
        try {
            logger.info("the file is moved to the general folder...");
            new Expectant(driver).waitingForTheMissingElementOnThePage(MOVING_FILE_PROGRESSBAR_LOCATOR);
            new Expectant(driver).waitingForAnElementOnThePage(MESSAGE_ABOUT_SUCCESSFUL_MOVE_LOCATOR);
            logger.info("file moved to general folder!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Failed to move file to main folder!***");
            return false;
        }
    }

    public boolean fileIsMovedToTrash() {
        try {
            logger.info("the file is moved to the trash...");
            new Expectant(driver).waitingForTheMissingElementOnThePage(MOVING_FILE_PROGRESSBAR_LOCATOR);
            logger.info("file is moved to trash!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***Failed to move file to trash***");
            return false;
        }
    }

    public boolean fileDeletedSuccessfully() {
        try {
            new Expectant(driver).waitingForTheMissingElementOnThePage(MESSAGE_ABOUT_SUCCESSFUL_MOVE_LOCATOR);
            logger.info("file deleted successfully!");
            return true;
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            logger.info("***The file remains on the page!***");
            return false;
        }
    }
}
