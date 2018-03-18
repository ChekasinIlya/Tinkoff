package ru.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainMenuPage extends BasePage{

    public String paymentName = "Платежи";

    public MainMenuPage (WebDriver driver){super(driver);}


    public WebElement GetWebElementMainMenuElement (String elementMenuName){
        By byMainMenuElement = By.xpath("//li[span[a[span[text()=" + "\"" + elementMenuName + "\"" + "]]]]");
        return driver.findElement(byMainMenuElement);
    }


    public void ClickOnMainMenuElement(String elementMenuName) throws InterruptedException {
        WebElement elementMenu = GetWebElementMainMenuElement(elementMenuName);
        ClickOnElement(elementMenu);
    }
}
