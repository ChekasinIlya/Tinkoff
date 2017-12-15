package ru.tinkoff.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.tinkoff.pages.*;
import java.io.IOException;

/**
 * Класс теста
 */
public class TestTinkoffBankSite extends BaseTest{

    @Test
    public void TestCase() throws IOException, InterruptedException {

        MainMenuPage menuPage = new MainMenuPage(getDriver());
        PaymentsPage payments = new PaymentsPage(getDriver());
        UtilitiesPaymentsPage uPPage = new UtilitiesPaymentsPage(getDriver());
        HCServicesMoscowPage hCSMPage = new HCServicesMoscowPage(getDriver());

        //2. Из верхнего меню, нажатием на пункт меню “Платежи“, перейти на страницу “Платежи“.
        menuPage.ClickOnMainMenuElement(menuPage.paymentName);

        //3. В списке категорий платежей, нажатием на пункт “Коммунальные платежи“, перейти на  страницу выбора поставщиков услуг.
        payments.ClickOnHousingAndUtilities(payments.paymentCategoryName);

        //4. Убедиться, что текущий регион – “г. Москва” (в противном случае выбрать регион “г. Москва” из списка регионов).
        uPPage.ChangeRegionTo("г. Москва","Москве");

        //5. Со страницы выбора поставщиков услуг, выбрать 1-ый из списка (Должен быть “ЖКУ-Москва”). Сохранить его наименование (далее “искомый”) и нажатием на соответствующий элемент перейти на страницу оплаты “ЖКУ-Москва“.
        String wanted = uPPage.SelectFirstServiceProvider();
        //Thread.sleep(3000);
        String uRL = driver.getCurrentUrl();

        //6. На странице оплаты, перейти на вкладку “Оплатить ЖКУ в Москве“.
        hCSMPage.GoToTheTabPayForHousingInMoscow();

        //7. Выполнить проверки на невалидные значения для обязательных полей: проверить все текстовые сообщения об ошибке (и их содержимое), которые появляются под соответствующим полем ввода в результате ввода некорректных данных.
        hCSMPage.FieldValidation();

        //8. Повторить шаг (2).
        menuPage.ClickOnMainMenuElement(menuPage.paymentName);

        //9. В строке быстрого поиска поставщика услуг ввести наименование искомого (ранее сохраненного).
        payments.SetQuickSearchBoxForServiceProvider(wanted);

        //10. Убедиться, что в списке предложенных провайдеров искомый поставщик первый
        WebElement firstElement = payments.CheckingFirstVendorInList(wanted);

        //11.1 Нажатием на элемент, соответствующий искомому, перейти на страницу “Оплатить ЖКУ в Москве“
        payments.ClickOnFirstElement(firstElement);

        //11.2 Убедиться, что загруженная страница та же, что и страница, загруженная в результате шага (5).
        //Thread.sleep(3000);
        Assert.assertEquals("The page is not the same as the result of step 5", uRL, driver.getCurrentUrl());

        //12. Выполнить шаги (2) и (3).
        menuPage.ClickOnMainMenuElement(menuPage.paymentName);
        payments.ClickOnHousingAndUtilities(payments.paymentCategoryName);

        //13. В списке регионов выбрать “г. Санкт-Петербург”.
        uPPage.ChangeRegionTo("г. Санкт-Петербург","Санкт-Петербурге");

        //14. Убедится, что в списке поставщиков на странице выбора поставщиков услуг отсутствует искомый.
        Assert.assertFalse("The required provider is present",uPPage.verificationOfSoughtSupplier(uPPage.sPName));
    }
}
