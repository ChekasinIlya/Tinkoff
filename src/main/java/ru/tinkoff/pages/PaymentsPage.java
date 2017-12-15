package ru.tinkoff.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Класс страницы Платежей
 */
public class PaymentsPage extends BasePage{

    @FindBy(xpath = "//input[contains(@class,\"Input__field_2XFoD Input__field_align_left_1phV_\")]")
    public WebElement input;

    public By dropDownList = By.xpath("//div[contains(@class,\"Grid__root_2zhCT Grid__root_display_block_2b7Gu\")]/div");
    public By internalDropDownList = By.xpath("div/div/div");

    public String paymentCategoryName = "ЖКХ";

    public PaymentsPage (WebDriver driver){
        super(driver);
    }

    /**
     * Метод отвечающий за получение элемента Категории платежей
     * Принимает на вход строку названия категории платежей
     * @param namePaymentCategoryElement
     * @return
     */
    public WebElement GetWebElementPaymentCategoryListElement(String namePaymentCategoryElement){
        By byPaymentCategoryElement = By.xpath("//a[contains(@title," + "\"" + namePaymentCategoryElement + "\"" + ")]");
        return driver.findElement(byPaymentCategoryElement);
    }

    /**
     * Метод отвечающий за клик на элемент категории платежей
     * Принимает строку названия категории платежей
     * @throws InterruptedException
     */
    public void ClickOnHousingAndUtilities(String paymentCategoryName) throws InterruptedException {
        WebElement housingAndUtilities = GetWebElementPaymentCategoryListElement(paymentCategoryName);
        scrollWithOffset(housingAndUtilities,0,150);
        ClickOnElement(housingAndUtilities);
    }

    /**
     * Метод отвечающий за ввод значения в строку быстрого поска
     * Принимает строку вводимого значения
     * @param wanted
     * @throws InterruptedException
     */
    public void SetQuickSearchBoxForServiceProvider(String wanted) throws InterruptedException {
        SetInput(input, wanted);
        expectation(driver.findElement(dropDownList));
    }

    /**
     * Метод осуществляющий проверку того, что искомый элемент является первым в выпадающем списке заполненной строки поиск
     * Примает на вход строку названия искомого элемента
     * Возвращает первый элемент
     * @param wanted
     * @return
     * @throws InterruptedException
     */
    public WebElement CheckingFirstVendorInList(String wanted) throws InterruptedException {
        WebElement firstElement = GetFirstElement(dropDownList, internalDropDownList);
        Assert.assertEquals("Sought supplier is not the first", wanted, firstElement.getText());
        return firstElement;
    }

    /**
     * Метод отвечающий за клик на первый элемент
     * Принимает на вход первый элемент
     * @param firstElement
     * @throws InterruptedException
     */
    public void ClickOnFirstElement(WebElement firstElement) throws InterruptedException {
        ClickOnElement(firstElement);
    }
}
