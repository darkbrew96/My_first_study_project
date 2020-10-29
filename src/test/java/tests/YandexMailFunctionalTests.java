package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.stormnet.yandex.pages.SearchPageEnvironment.SearchPage;
import ru.stormnet.yandex.pages.YandexDiscEnvironment.YandexDiscPage;
import ru.stormnet.yandex.pages.loginPageEnvironment.LoginPage;
import ru.stormnet.yandex.pages.mailPageEnvironment.MailPage;
import ru.stormnet.yandex.pages.mailPageEnvironment.NewLetterPopUp;
import ru.stormnet.yandex.utills.FileManager;
import ru.stormnet.yandex.utills.PropertiesManager;

import java.io.File;
import java.io.IOException;


@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class YandexMailFunctionalTests extends AbstractTest {
    File fileToSend;

    @BeforeMethod

    public void setupForYMFTests() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String username = PropertiesManager.getProperty("correct_username");
        String password = PropertiesManager.getProperty("correct_password");
        loginPage.loginWithCreeds(username, password);
        MailPage mailPage = new MailPage(driver);
        Assert.assertTrue(mailPage.isOpened(), "Unsuccessful page opening!");
        fileToSend = FileManager.createFile();
    }

    @Test
    public void yandexMailFunctionalTest() {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        newLetterPopUp.attachFileToMessage(fileToSend);
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(), "Failed to file attachments");
        newLetterPopUp.sendMessageToRecipient();

    }

    @Test
    public void yandexMailFunctionalDragAndDropTest() {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        YandexDiscPage yandexDiscPage = new YandexDiscPage(driver);
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        newLetterPopUp.attachFileToMessage(fileToSend);
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(), "Failed to file attachments!");
        newLetterPopUp.sendMessageToRecipient();
        mailPage.refreshPage();
        mailPage.refreshPage();//periodically there is a situation that when the page is updated, the letter with the file
        // does not appear, looking for a solution to this problem.
        mailPage.saveFileToYandexDisc();
        mailPage.goToYandexDiscPage();
        Assert.assertTrue(yandexDiscPage.isOpened(), "Unsuccessful Yandex Disc opening!");
        yandexDiscPage.openFolderWithDownloads();
        yandexDiscPage.dragAndDropFileToGeneralFolder();
        Assert.assertTrue(yandexDiscPage.fileIsMovedToGeneralFolder(), "Failed to move file to main folder!");
        yandexDiscPage.openGeneralFolder();
        yandexDiscPage.refreshPage();
        yandexDiscPage.dragAndDropFileToTrash(fileToSend);
        Assert.assertTrue(yandexDiscPage.fileIsMovedToTrash(), "Failed to move file to trash!");
        Assert.assertTrue(yandexDiscPage.fileDeletedSuccessfully(), "The file remains on the page!");
    }

    @AfterMethod

    public void afterMethod() throws IOException {
        FileManager.deleteFile();
    }
}
