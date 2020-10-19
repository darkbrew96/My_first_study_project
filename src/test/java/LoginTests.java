import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.stormnet.yandex.pages.LoginPage;
import ru.stormnet.yandex.pages.MailPage;
import ru.stormnet.yandex.pages.SearchPage;
import ru.stormnet.yandex.utills.PropertiesManager;


@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class LoginTests extends AbstractTest {

    @BeforeMethod
    public void setupForLoginTests() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goToLoginPage();
    }

    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        String username = PropertiesManager.getProperty("correct_username");
        String password = PropertiesManager.getProperty("correct_password");
        loginPage.loginWithCreeds(username, password);

        MailPage mailPage = new MailPage(driver);
        Assert.assertTrue(mailPage.isOpened(),"Mail page is not open!");//добавить месэдж о провале
    }

    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        String username = PropertiesManager.getProperty("correct_username");
        String password = PropertiesManager.getProperty("uncorrect_password");
        loginPage.loginWithCreeds(username, password);

        MailPage mailPage = new MailPage(driver);
        Assert.assertFalse(mailPage.isOpened(), "Mail page is open!");
    }
}
