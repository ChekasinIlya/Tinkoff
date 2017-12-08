import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class PageTest {

    WebDriver driver;
    By payments = By.xpath("//li[contains(@data-menu-item,\"2\")]");
    By communalPayments = By.xpath("//a[contains(@title,\"Коммунальные платежи\")]");
    By regions = By.xpath("//span[contains(@class,\"ui-link payment-page__title_inner\")]");
    By moscow = By.xpath("//span[text()=\"г. Москва\"]");
    By serviceProviders = By.xpath("//ul[contains(@class,\"ui-menu ui-menu_icons UIPayments__categoryProviders_3Fsrs\")]/li");
    By internalProviders = By.xpath("span/a");
    By PayHousingServicesInMoscow = By.xpath("//span[text()=\"Оплатить ЖКУ в Москве\"]/parent::*");
    By input = By.xpath("//input[contains(@class,\"Input__field_2XFoD Input__field_align_left_1phV_\")]");
    By dropDownList = By.xpath("//div[contains(@class,\"Grid__root_2zhCT Grid__root_display_block_2b7Gu\")]/div");
    By internalDropDownList = By.xpath("div/div/div");
    By stPetersburg = By.xpath("//span[text()=\"г. Санкт-Петербург\"]");
    By wanted = By.xpath("//div[text()=\"ЖКУ-Москва\"]");
    By payerCode = By.xpath("//input[@name=\"provider-payerCode\"]");
    By payerCodeError = By.xpath("//div[@data-qa-file=\"UIInput\"]/parent::*/div[@data-qa-file=\"UIFormRowError\"]");
    By providerPeriod = By.xpath("//input[@name=\"provider-period\"]");
    By providerPeriodError = By.xpath("//div[@data-qa-file=\"UIInputDate\"]/parent::*/div[@data-qa-file=\"UIFormRowError\"]");
    By amountOfPayment = By.xpath("//div[@data-qa-file=\"FormFieldSet\"]//input");
    By amountOfPaymentError = By.xpath("//div[@data-qa-file=\"FormFieldSet\"]//div[@data-qa-file=\"UIFormRowError\"]");

    public PageTest (WebDriver driver){
        this.driver = driver;
    }

    public String getErrorMasage (By Input, By error, String inputVal) throws InterruptedException {
        expectation(Input);
        WebElement elementInput = driver.findElement(Input);
        elementInput.sendKeys(inputVal);
        elementInput.sendKeys(Keys.ENTER);

        expectation(error);
        WebElement elementError = driver.findElement(error);
        return elementError.getText();
    }

    public void changeRegion(By region, By cityToChange, String city) throws InterruptedException {
        expectation(region);
        WebElement elementRegions = driver.findElement(region);
        String regionText = elementRegions.getText();
        if(regionText.equals(city)) {}
        else{
            elementRegions.click();
            expectation(cityToChange);
            WebElement moskowElement = driver.findElement(cityToChange);
            moskowElement.click();
        }
    }

    public void ClickOnElement(By element) throws InterruptedException {
        expectation(element);
        WebElement paymentsElement = driver.findElement(element);
        paymentsElement.click();
    }

    public String getServiceProviderName (WebElement serviceProvider){
        String serviceProviderName = serviceProvider.getAttribute("title");
        return serviceProviderName;
    }

    public WebElement getFirstElement (By listElements, By internalLocator) {
        List<WebElement> ElementsList = driver.findElements(listElements);
        WebElement firstOnList = ElementsList.get(0);
        WebElement first = firstOnList.findElement(internalLocator);
        return first;
    }

     public boolean verificationOfSoughtSupplier (){
        WebElement providerListed;
        for (WebElement serviceProvidersElement:(driver.findElements(serviceProviders))) {
            providerListed = serviceProvidersElement.findElement(By.tagName("span")).findElement(By.tagName("a"));
            if (providerListed.getAttribute("title").equals("ЖКУ-Москва")) {
                return true;
            }
        }
        return false;
    }

    public void scrollWithOffset(By scrollBy, int x, int y) {

        WebElement ByScroll = driver.findElement(scrollBy);
        String code = "window.scroll(" + (ByScroll.getLocation().x - x) + ","
                + (ByScroll.getLocation().y - y) + ");";

        ((JavascriptExecutor)driver).executeScript(code, ByScroll, x, y);
    }

    public void expectation(By element) throws InterruptedException {
        WebDriverWait dynamicElement = new WebDriverWait(driver, 10);
        dynamicElement.until(ExpectedConditions.presenceOfElementLocated(element));
        Thread.sleep(3000);
    }
}