package tests;

import org.testng.Assert;
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


@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class YandexMailFunctionalTests extends AbstractTest {


    @BeforeMethod

    public void setupForYMFTests() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String username = PropertiesManager.getProperty("correct_username");
        String password = PropertiesManager.getProperty("correct_password");
        loginPage.loginWithCreeds(username, password);//добавить ассерт
        MailPage mailPage = new MailPage(driver);
        Assert.assertTrue(mailPage.isOpened(), "Unsuccessful page opening!");
    }

    @Test
    public void yandexMailFunctionalTest() {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(), "Failed to file attachments");
        newLetterPopUp.sendMessageToRecipient();
        FileManager.deleteFile();

    }

    @Test
    public void yandexMailFunctionalDragAndDropTest() throws InterruptedException {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        YandexDiscPage yandexDiscPage = new YandexDiscPage(driver);
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        newLetterPopUp.attachFileToMessage();
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(), "Failed to file attachments!");
        newLetterPopUp.sendMessageToRecipient();
        FileManager.deleteFile();
        mailPage.refreshPage();
        mailPage.saveFileToYandexDisc();
        mailPage.goToYandexDiscPage();
        yandexDiscPage.openFolderWithDownloads();
        yandexDiscPage.dragAndDropFileToGeneralFolder();
        Assert.assertTrue(yandexDiscPage.fileIsMovedToGeneralFolder(), "Failed to move file to main folder!");
        yandexDiscPage.openGeneralFolder();
        Thread.sleep(500);
        yandexDiscPage.refreshPage();
        Thread.sleep(500);
        yandexDiscPage.dragAndDropFileToTrash();
        Assert.assertTrue(yandexDiscPage.fileIsMovedToTrash(), "Failed to move file to trash");
    }
}
