package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stormnet.yandex.utills.PropertiesManager;

public class AbstractTest {
    public static final Logger logger = LogManager.getLogger(YandexMailFunctionalTests.class);
    protected WebDriver driver;

    @BeforeMethod

    public void setupForTests(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(PropertiesManager.getProperty("search_page_url"));
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void afterTheEndOfTheTests() {
        driver.close();
        driver.quit();
    }
}
