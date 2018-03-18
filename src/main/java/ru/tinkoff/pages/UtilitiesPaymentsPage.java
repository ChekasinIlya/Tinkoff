package ru.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class UtilitiesPaymentsPage extends BasePage{

    @FindBy(xpath = "//span[contains(@class,\"ui-link payment-page__title_inner\")]")
    public WebElement regions;

    public By serviceProviders = By.xpath("//ul[contains(@class,\"ui-menu ui-menu_icons UIPayments__categoryProviders_3Fsrs\")]/li");
    public By internalProviders = By.xpath("span/a");

    public UtilitiesPaymentsPage (WebDriver driver){
        super(driver);
    }

    public String sPName = "ЖКУ-Москва";

    public WebElement GetWebElementCity(String nameCity){
        By byElementCity = By.xpath("//span[text()=" + "\"" + nameCity + "\"" + "]");
        return driver.findElement(byElementCity);
    }

    public void ChangeRegionTo(String region, String city) throws InterruptedException {
        String regionText = GetElementText(regions);
        if(regionText.equals(city)) {}
        else{
            ClickOnElement(regions);
            WebElement cityToChange = GetWebElementCity(region);
            ClickOnElement(cityToChange);
            expectation(regions);
        }
    }

    public String GetServiceProviderName (WebElement serviceProvider){
        String serviceProviderName = serviceProvider.getAttribute("title");
        return serviceProviderName;
    }

    public String SelectFirstServiceProvider() throws InterruptedException {
        WebElement firstElementUl = GetFirstElement(serviceProviders, internalProviders);
        String wanted = GetServiceProviderName(firstElementUl);
        ClickOnElement(firstElementUl);
        return wanted;
    }

    public boolean verificationOfSoughtSupplier (String sPName){
        WebElement providerListed;
        for (WebElement serviceProvidersElement:(driver.findElements(serviceProviders))) {
            providerListed = serviceProvidersElement.findElement(By.tagName("span")).findElement(By.tagName("a"));
            if (providerListed.getAttribute("title").equals(sPName)) {
                return true;
            }
        }
        return false;
    }
}
