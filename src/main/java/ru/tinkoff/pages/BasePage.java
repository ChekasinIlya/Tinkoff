package ru.tinkoff.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * Базовый класс всех страниц
 */

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод отвечающий за ожидание загрузки элемента на странице
     * Принимает элемент, загрузку которого необходимо дождаться
     * @param element
     * @throws InterruptedException
     */
    public void expectation(WebElement element) throws InterruptedException {
        WebDriverWait dynamicElement = new WebDriverWait(driver, 10);
        dynamicElement.until(ExpectedConditions.visibilityOf(element));
        Thread.sleep(2000);
    }

    /**
     * Метод отвечающий за прокрутку страницы к выбранному элементу с заданным смещением
     * Принимает элемент на вход элемент и два значения в пикселях для смещения по осям x и y
     * @param element
     * @param x
     * @param y
     * @throws InterruptedException
     */
    public void scrollWithOffset(WebElement element, int x, int y) throws InterruptedException {
        expectation(element);
        String code = "window.scroll(" + (element.getLocation().x - x) + ","
                + (element.getLocation().y - y) + ");";
        ((JavascriptExecutor)driver).executeScript(code, element, x, y);
    }

    /**
     * Метод отвечающий за клик на элемент
     * Принимает на вход элемент на который необходимо кликнуть
     * @param element
     * @throws InterruptedException
     */
    public void ClickOnElement(WebElement element) throws InterruptedException {
        expectation(element);
        element.click();
    }

    /**
     * Метод отвечающий за получение атрибута text() переданного в него элемента
     * @param element
     * @return
     * @throws InterruptedException
     */
    public String GetElementText(WebElement element) throws InterruptedException {
        expectation(element);
        return element.getText();
    }

    /**
     * Метод отвечающий за внесение значения в input
     * Принимает на вход элемент input и строку значения которое необходимо внемсти
     * @param input
     * @param value
     * @throws InterruptedException
     */
    public void SetInput(WebElement input, String value) throws InterruptedException {
        expectation(input);
        input.sendKeys(value);
    }

    /**
     * Метод отвечающий за получение первого элемента из списка
     * Принимает на вход By списка и By элемента внутри списка
     * Возвращает первый элемент из списка
     * @param listElements
     * @param internalLocator
     * @return
     * @throws InterruptedException
     */
    public WebElement GetFirstElement (By listElements, By internalLocator) throws InterruptedException {
        expectation(driver.findElement(listElements));
        List<WebElement> ElementsList = driver.findElements(listElements);
        WebElement firstOnList = ElementsList.get(0);
        WebElement first = firstOnList.findElement(internalLocator);
        return first;
    }
}
