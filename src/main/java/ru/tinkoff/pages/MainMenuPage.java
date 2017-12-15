package ru.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс основного меню сайта
 */
public class MainMenuPage extends BasePage{

    /**
     * Название пункта меню
     */
    public String paymentName = "Платежи";

    public MainMenuPage (WebDriver driver){super(driver);}

    /**
     * Метод отвечающий за получение элемента основного меню
     * Принимает на вход строку названия элемента меню
     * Возвращает элемент меню
     * @param elementMenuName
     * @return
     */
    public WebElement GetWebElementMainMenuElement (String elementMenuName){
        By byMainMenuElement = By.xpath("//li[span[a[span[text()=" + "\"" + elementMenuName + "\"" + "]]]]");
        return driver.findElement(byMainMenuElement);
    }

    /**
     * Метод отвечающий за переход по пункту основного меню
     * Принимает на вход название пункта меню
     * @param elementMenuName
     * @throws InterruptedException
     */
    public void ClickOnMainMenuElement(String elementMenuName) throws InterruptedException {
        WebElement elementMenu = GetWebElementMainMenuElement(elementMenuName);
        ClickOnElement(elementMenu);
    }
}
