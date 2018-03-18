package ru.tinkoff.tests;

import org.junit.Test;
import ru.tinkoff.pages.MainMenuPage;
import ru.tinkoff.pages.PaymentsPage;

import java.io.IOException;

/**
 * Класс теста
 */
public class TestTinkoffBankSite extends BaseTest{

    @Test
    public void TestCase() throws IOException, InterruptedException {

        MainMenuPage menuPage = new MainMenuPage(getDriver());
        PaymentsPage payments = new PaymentsPage(getDriver());

        //Из верхнего меню, нажатием на пункт меню “Платежи“, перейти на страницу “Платежи“.
        menuPage.ClickOnMainMenuElement(menuPage.paymentName);

        //В списке категорий платежей, нажатием на пункт “Коммунальные платежи“, перейти на  страницу выбора поставщиков услуг.
        payments.ClickOnHousingAndUtilities(payments.paymentCategoryName);
    }
}
