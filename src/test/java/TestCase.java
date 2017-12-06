import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestCase {

    private static WebDriver driver;
    private static PageTest page;

    @BeforeClass
    public static void setup(){
        driver = new ChromeDriver();
        page = new PageTest(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get("http://trends.google.ru/trends");
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Title("This is test suite")
    @Test
    public void TestCas() throws IOException, InterruptedException {

        //Скрин
        page.skrin("1");
        //Запрос по Точка Банк
        page.SetInTopics("Точка Банк");

        //Фильтр по Россия
        String rus = "Россия";
        page.FilterByRegion(rus);

        //Фильтр по 90 дней
        page.TimeFilter(page.for90Days);

        //Thread.sleep(2000);

        //Фильтр по Город
        page.scrollWithOffset(page.PopularityByRegion, 0, 150);
        page.FilterByCityOrRegion(page.city);

        Thread.sleep(3000);
        page.skrin("2");

        page.scrollWithOffset(page.DynamicsOfPopularity, 0, 150);
        page.skrin("3");

        page.scrollWithOffset(page.MoreOnTheTopicAndInTheTrend, 0, 0);
        page.skrin("4");

        //Регион Уганда
        page.scrollWithOffset(page.up, 0, 0);
        String ug = "Уганда";
        page.FilterByRegion(ug);

        Thread.sleep(1000);

        //Проверка ошибки
        page.scrollWithOffset(page.DynamicsOfPopularity, 0, 150);
        String actualError = page.ErrorCheck(page.error);
        String expectedError = "Данных по этому запросу слишком мало.";
        Assert.assertEquals("incorrect error text", expectedError, actualError);
        page.skrin("5");
    }
}
