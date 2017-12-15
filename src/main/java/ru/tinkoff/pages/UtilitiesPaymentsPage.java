package ru.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы Коммунальные платежи
 */
public class UtilitiesPaymentsPage extends BasePage{

    @FindBy(xpath = "//span[contains(@class,\"ui-link payment-page__title_inner\")]")
    public WebElement regions;

    public By serviceProviders = By.xpath("//ul[contains(@class,\"ui-menu ui-menu_icons UIPayments__categoryProviders_3Fsrs\")]/li");
    public By internalProviders = By.xpath("span/a");

    public UtilitiesPaymentsPage (WebDriver driver){
        super(driver);
    }

    public String sPName = "ЖКУ-Москва";

    /**
     * Метод отвечающий за получение элемента Региона
     * @param nameCity
     * @return
     */
    public WebElement GetWebElementCity(String nameCity){
        By byElementCity = By.xpath("//span[text()=" + "\"" + nameCity + "\"" + "]");
        return driver.findElement(byElementCity);
    }

    /**
     * Метод отвечающий за изменение региона на заданный, если текущий регион не соответствует заданному
     * @param region
     * @param city
     * @throws InterruptedException
     */
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

    /**
     * Метод отвечающий за получение имени сервис-провайдера
     * Принимает на вход элемент серсис-провайдера
     * Возвращает строку имени сервис-провайдера
     * @param serviceProvider
     * @return
     */
    public String GetServiceProviderName (WebElement serviceProvider){
        String serviceProviderName = serviceProvider.getAttribute("title");
        return serviceProviderName;
    }

    /**
     * Метод отвечающий за получение имени первого сервис-провайдера
     * Возвращает строку имени первыого сервис-провайдера
     * @return
     * @throws InterruptedException
     */
    public String SelectFirstServiceProvider() throws InterruptedException {
        WebElement firstElementUl = GetFirstElement(serviceProviders, internalProviders);
        String wanted = GetServiceProviderName(firstElementUl);
        ClickOnElement(firstElementUl);
        return wanted;
    }

    /**
     * Метод осуществляющий проверку того, что в списке сервис-провайдеров отсутствует искомый
     * Принимает на вход строку имени искомого сервис-провайдера
     * Возвращает true - если сервис-провайдер найден в списке
     * Возвращает false - если сервис-провайдер не найден в списке
     * @param sPName
     * @return
     */
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
