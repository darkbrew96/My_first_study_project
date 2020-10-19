import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.stormnet.yandex.pages.*;
import ru.stormnet.yandex.utills.FileManager;
import ru.stormnet.yandex.utills.PropertiesManager;


@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class YandexMailFunctionalTests extends AbstractTest {

    private static final Logger logger = LogManager.getLogger(YandexMailFunctionalTests.class);

    @BeforeMethod

    public void setupForYMFTests(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String username = PropertiesManager.getProperty("correct_username");
        String password = PropertiesManager.getProperty("correct_password");
        loginPage.loginWithCreeds(username, password);//добавить ассерт
        MailPage mailPage = new MailPage(driver);
        Assert.assertTrue(mailPage.isOpened());//меседж
    }

    @Test
    public void yandexMailFunctionalTest() {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        FileManager.createFile();
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(),"Failed to file attachments");
        newLetterPopUp.sendMessageToRecipient();
        FileManager.deleteFile();

    }

    @Test
    public void yandexMailFunctionalDragAndDropTest() throws InterruptedException {
        MailPage mailPage = new MailPage(driver);
        NewLetterPopUp newLetterPopUp = new NewLetterPopUp(driver);
        YandexDiscPage yandexDiscPage = new YandexDiscPage(driver);
        FileManager.createFile();
        mailPage.openLetterPop_Up();
        newLetterPopUp.enterAddress();
        newLetterPopUp.attachFileToMessage();
        Assert.assertTrue(newLetterPopUp.allFileIsAttached(),"Failed to file attachments");
        newLetterPopUp.sendMessageToRecipient();
        FileManager.deleteFile();
        mailPage.refreshPage();
        mailPage.saveFileToYandexDisc();
        mailPage.goToYandexDiscPage();
        yandexDiscPage.openFolderWithDownloads();
        yandexDiscPage.dragAndDropFileToGeneralFolder();
        yandexDiscPage.openGeneralFolder();
        Thread.sleep(3000);
        yandexDiscPage.refreshPage();
        Thread.sleep(3000);
        yandexDiscPage.dragAndDropFileToTrash();



    }
}
//драгондроп (проверить что файл удалился)
//ридми файл
//скриншот
//гитигнор
