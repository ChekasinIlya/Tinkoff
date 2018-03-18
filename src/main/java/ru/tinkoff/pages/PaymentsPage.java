package ru.tinkoff.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PaymentsPage extends BasePage{

    @FindBy(xpath = "//input[contains(@class,\"Input__field_2XFoD Input__field_align_left_1phV_\")]")
    public WebElement input;

    public By dropDownList = By.xpath("//div[contains(@class,\"Grid__root_2zhCT Grid__root_display_block_2b7Gu\")]/div");
    public By internalDropDownList = By.xpath("div/div/div");

    public String paymentCategoryName = "ЖКХ";

    public PaymentsPage (WebDriver driver){
        super(driver);
    }

    public WebElement GetWebElementPaymentCategoryListElement(String namePaymentCategoryElement){
        By byPaymentCategoryElement = By.xpath("//a[contains(@title," + "\"" + namePaymentCategoryElement + "\"" + ")]");
        return driver.findElement(byPaymentCategoryElement);
    }

    public void ClickOnHousingAndUtilities(String paymentCategoryName) throws InterruptedException {
        WebElement housingAndUtilities = GetWebElementPaymentCategoryListElement(paymentCategoryName);
        scrollWithOffset(housingAndUtilities,0,150);
        ClickOnElement(housingAndUtilities);
    }

    public void SetQuickSearchBoxForServiceProvider(String wanted) throws InterruptedException {
        SetInput(input, wanted);
        expectation(driver.findElement(dropDownList));
    }

    public WebElement CheckingFirstVendorInList(String wanted) throws InterruptedException {
        WebElement firstElement = GetFirstElement(dropDownList, internalDropDownList);
        Assert.assertEquals("Sought supplier is not the first", wanted, firstElement.getText());
        return firstElement;
    }

    public void ClickOnFirstElement(WebElement firstElement) throws InterruptedException {
        ClickOnElement(firstElement);
    }
}
