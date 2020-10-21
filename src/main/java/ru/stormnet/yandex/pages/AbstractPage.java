package ru.stormnet.yandex.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;


    public static final Logger logger = LogManager.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {

        this.driver = driver;
    }
}
