package ru.tinkoff.tests;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Базовый класс всех тестов
 */
public class BaseTest {

    protected WebDriver driver;
    protected String baseURL = "https://www.tinkoff.ru/";

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        initializeDriver();
        setPropertyWindow();
        setPropertyTimeOut();
        goToURL(baseURL);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    public void initializeDriver() {
        driver = new ChromeDriver();
    }

    public void setPropertyWindow() {
        driver.manage().window().maximize();
    }

    public void setPropertyTimeOut() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    public void goToURL (String URL){ driver.get(URL); }
}
