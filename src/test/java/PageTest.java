import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;


public class PageTest {

    WebDriver driver;
    By topics = By.id("input-0");
    By worldwide = By.xpath("//hierarchy-picker[contains(@track-name,\"geoPicker\")]");
    By regionInput = By.id("input-9");
    By goinput = By.xpath("//li[contains(@md-virtual-repeat,\"item in $mdAutocompleteCtrl.matches\")][@role=\"button\"]");
    By timeFilter = By.xpath("//md-select-value[contains(@class,\"_md-select-value\")]");
    By for90Days = By.xpath("//div[contains(text(),\"За 90 дней\")]/parent::*");
    By subfilterPopularityBySubregion = By.xpath("//md-select[contains(@aria-label,\"Субрегион\")]");
    By city = By.xpath("//md-option[@value='city']");
    By error = By.xpath("//p[contains(@class,\"widget-error-title\")]");

    By up = By.xpath("//input[@id=\"input-25\"]");
    By DynamicsOfPopularity = By.xpath("//div[contains(@class,\"fe-line-chart-header-title\")]");
    By PopularityByRegion = By.xpath("//div[contains(text(),\"Популярность по субрегионам\")]");
    By MoreOnTheTopicAndInTheTrend = By.xpath("//div[contains(@class,\"fe-atoms-generic-title\")][contains(text(),\"Ещё по теме\")]");


    public PageTest (WebDriver driver){
        this.driver = driver;
    }

    //Поисковой запрос
    public void SetInTopics(String topic){
        WebElement elementtopic = driver.findElement(topics);
        elementtopic.sendKeys(topic);
        elementtopic.sendKeys(Keys.ENTER);
    }

    //Фильтр по региону
    public void FilterByRegion(String region) throws InterruptedException {
        WebElement woldwide = driver.findElement(worldwide);
        woldwide.click();

        WebElement inputreg = driver.findElement(regionInput);
        inputreg.sendKeys(region);

        WebElement russia = driver.findElement(goinput);
        russia.click();
    }

    //Фильтр по времени
    public void TimeFilter(By time) throws InterruptedException {
        WebElement timefilt = driver.findElement(timeFilter);
        timefilt.click();

        WebElement ElementTime = driver.findElement(time);
        ElementTime.click();
    }

    //Фильтр по городу в окне “Популярность по субрегионам”
    public void FilterByCityOrRegion (By cityOrRegion) throws InterruptedException {
        WebElement subFilter = driver.findElement(subfilterPopularityBySubregion);
        subFilter.click();

        WebElement elementCity = driver.findElement(cityOrRegion);
        elementCity.click();
    }

    public String ErrorCheck (By errorElement) {
        WebElement ElementError = driver.findElement(errorElement);
        String errorText = ElementError.getText();
        return errorText;
    }


    public void scrollWithOffset(By scrollBy, int x, int y) {

        WebElement ByScroll = driver.findElement(scrollBy);
        String code = "window.scroll(" + (ByScroll.getLocation().x - x) + ","
                + (ByScroll.getLocation().y - y) + ");";

        ((JavascriptExecutor)driver).executeScript(code, ByScroll, x, y);

    }

    public void skrin (String name) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pathname = "C:\\test\\" + name + ".png";
        FileUtils.copyFile(scrFile, new File(pathname));
    }
}