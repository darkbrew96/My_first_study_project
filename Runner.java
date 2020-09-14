import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.By.*;

public class Runner {

    public static void main (String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start[-maximized]");

        driver.get("https://catalog.onliner.by/");
//        driver.get("https://cart.onliner.by/");
        WebElement appliancesButton = driver.findElement(cssSelector(" div.catalog-navigation.catalog-navigation_opened > ul > li:nth-child(4)"));
        appliancesButton.click();
        Thread.sleep(3000);
        WebElement coffeAndTeaButton = driver.findElement(cssSelector(" div > div:nth-child(3) > div.catalog-navigation-list__aside > div > div:nth-child(7)"));
        coffeAndTeaButton.click();
        Thread.sleep(3000);
        WebElement coffeMachineButton = driver.findElement(cssSelector(" div > div.catalog-navigation-list__aside-item.catalog-navigation-list__aside-item_active > div.catalog-navigation-list__dropdown > div > a:nth-child(1)"));
        coffeMachineButton.click();
        Thread.sleep(3000);
        WebElement coffevarkaButton = driver.findElement(cssSelector("#schema-products > div:nth-child(1) > div > div.schema-product__part.schema-product__part_1"));
        coffevarkaButton.click();
        Thread.sleep(3000);
        WebElement dobavlenieVKorzinuButton = driver.findElement(cssSelector(" div > div.product-aside__item.product-aside__item--highlighted.state_add-to-cart.product-aside__item--byn > div > div:nth-child(1) > a.button.button_orange.product-aside__item-button"));
        dobavlenieVKorzinuButton.click();
        Thread.sleep(3000);
        dobavlenieVKorzinuButton.click();
        WebElement predmetVKorzive = driver.findElement(cssSelector("div > div.cart-form__offers-part.cart-form__offers-part_image > a"));
        if (predmetVKorzive.isDisplayed()) {
            System.out.println("Предмет добавлен в корзину");
        } else {
            System.out.println("Предмет не добавлен в корзину");
        }
        Thread.sleep(10000);
        driver.close();
    }
}
