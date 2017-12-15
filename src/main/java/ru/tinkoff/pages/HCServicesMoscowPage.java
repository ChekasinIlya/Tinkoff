package ru.tinkoff.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы ЖКУ-Москва
 */
public class HCServicesMoscowPage extends BasePage{

    @FindBy(xpath = "//input[@name=\"provider-payerCode\"]")
    public WebElement payerCode;

    @FindBy(xpath = "//div[@data-qa-file=\"UIInput\"]/parent::*/div[@data-qa-file=\"UIFormRowError\"]")
    public WebElement payerCodeError;

    @FindBy(xpath = "//input[@name=\"provider-period\"]")
    public WebElement providerPeriod;

    @FindBy(xpath = "//div[@data-qa-file=\"UIInputDate\"]/parent::*/div[@data-qa-file=\"UIFormRowError\"]")
    public WebElement providerPeriodError;

    @FindBy(xpath = "//div[@data-qa-file=\"FormFieldSet\"]//input")
    public WebElement amountOfPayment;

    @FindBy(xpath = "//div[@data-qa-file=\"FormFieldSet\"]//div[@data-qa-file=\"UIFormRowError\"]")
    public WebElement amountOfPaymentError;

    private String errorMassageCompulsory = "Поле обязательное";
    private String errorMassageIncorrectly2 = "Поле неправильно заполнено";
    private String errorMassageIncorrectly = "Поле заполнено некорректно";
    private String errorMassageMaximumAmount = "Максимальная сумма перевода — 15 000 \u20BD";
    private String errorMassageMinimumAmount = "Минимальная сумма перевода — 10 \u20BD";

    public HCServicesMoscowPage (WebDriver driver){
        super(driver);
    }

    /**
     * Метод отвечающий за ввод значения в input и получение сообщения ошибки по введенному значению
     * Принимает элемент input, элемент error и строку значения для ввода в input
     * Возвращает текст ошибки
     * @param input
     * @param error
     * @param inputValue
     * @return
     * @throws InterruptedException
     */
    private String GetErrorMasage(WebElement input, WebElement error, String inputValue) throws InterruptedException {
        SetInput(input, inputValue);
        input.sendKeys(Keys.ENTER);
        return GetElementText(error);
    }

    /**
     * Метод отвечающий за получение элемент вкладки
     * Принимает строку названия вкладки
     * Возвращает элемент вкладки
     * @param nameTabsOnPageOfHCSMoscow
     * @return
     */
    private WebElement GetWebElementTabsOnPageOfHCSMoscow(String nameTabsOnPageOfHCSMoscow){
        By byTabsOnPageOfHCSMoscowElement = By.xpath("//span[text()=" + "\"" + nameTabsOnPageOfHCSMoscow + "\"" + "]/parent::*");
        return driver.findElement(byTabsOnPageOfHCSMoscowElement);
    }

    /**
     * Метод отвечающий за переход на вкладку "Оплатить ЖКУ в Москве"
     * @throws InterruptedException
     */
    public void GoToTheTabPayForHousingInMoscow () throws InterruptedException {
        WebElement payHousingServicesInMoscow = GetWebElementTabsOnPageOfHCSMoscow("Оплатить ЖКУ в Москве");
        ClickOnElement(payHousingServicesInMoscow);
    }

    /**
     * Метод совершающий проверки на невалидные значения обязательных полей
     * Поочередно вводит в каждое из полей невалидное значение и проверяет корректность сообщения об ошибке
     * @throws InterruptedException
     */
    public void FieldValidation() throws InterruptedException {

        Assert.assertEquals("The error message is not correct in field \"Payer code for housing and communal services in Moscow\"", errorMassageCompulsory, GetErrorMasage(payerCode, payerCodeError, ""));
        Assert.assertEquals("The error message is not correct in field \"For what period do you pay for utilities\"", errorMassageCompulsory, GetErrorMasage(providerPeriod, providerPeriodError, ""));
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageCompulsory, GetErrorMasage(amountOfPayment, amountOfPaymentError, ""));
        driver.navigate().refresh();

        Assert.assertEquals("The error message is not correct in field \"Payer code for housing and communal services in Moscow\"", errorMassageCompulsory, GetErrorMasage(payerCode, payerCodeError, "hп(*%;№!/.="));
        Assert.assertEquals("The error message is not correct in field \"For what period do you pay for utilities\"", errorMassageCompulsory, GetErrorMasage(providerPeriod, providerPeriodError, "hп(*%;№!/.="));
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageCompulsory, GetErrorMasage(amountOfPayment, amountOfPaymentError, "hп(*%;№!/="));
        driver.navigate().refresh();

        Assert.assertEquals("The error message is not correct in field \"Payer code for housing and communal services in Moscow\"", errorMassageIncorrectly2, GetErrorMasage(payerCode, payerCodeError, "11111"));
        Assert.assertEquals("The error message is not correct in field \"For what period do you pay for utilities\"", errorMassageIncorrectly, GetErrorMasage(providerPeriod, providerPeriodError, "132017"));
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMaximumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "15001"));
        driver.navigate().refresh();

        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9.9"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9,9"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9,"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9,9,9"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMinimumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "9,99"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMaximumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "15000.1"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassageMaximumAmount, GetErrorMasage(amountOfPayment, amountOfPaymentError, "15000.01"));
    }
}
