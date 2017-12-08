import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TestCase {

    private static WebDriver driver;
    private static PageTest page;

    @BeforeClass
    public static void setup(){
        driver = new ChromeDriver();
        page = new PageTest(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        //1. Переходом по адресу https://www.tinkoff.ru/ загрузить стартовую страницу Tinkoff Bank
        driver.get("https://www.tinkoff.ru/");
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void TestCas() throws IOException, InterruptedException {

        //2. Из верхнего меню, нажатием на пункт меню “Платежи“, перейти на страницу “Платежи“.
        page.ClickOnElement(page.payments);

        //3. В списке категорий платежей, нажатием на пункт “Коммунальные платежи“, перейти на  страницу выбора поставщиков услуг.
        page.scrollWithOffset(page.communalPayments,0,150);
        page.ClickOnElement(page.communalPayments);

        //4. Убедиться, что текущий регион – “г. Москва” (в противном случае выбрать регион “г. Москва” из списка регионов).
        String Moscow = "Москве";
        page.changeRegion(page.regions, page.moscow, Moscow);
        page.expectation(page.serviceProviders);

        //5. Со страницы выбора поставщиков услуг, выбрать 1-ый из списка (Должен быть “ЖКУ-Москва”). Сохранить его наименование (далее “искомый”) и нажатием на соответствующий элемент перейти на страницу оплаты “ЖКУ-Москва“.
        WebElement firstElementUl = page.getFirstElement(page.serviceProviders, page.internalProviders);
        String wanted = page.getServiceProviderName(firstElementUl);
        firstElementUl.click();

        Thread.sleep(3000);
        String URL = driver.getCurrentUrl();

        //6. На странице оплаты, перейти на вкладку “Оплатить ЖКУ в Москве“.
        page.ClickOnElement(page.PayHousingServicesInMoscow);

        //7. Выполнить проверки на невалидные значения для обязательных полей: проверить все текстовые сообщения об ошибке (и их содержимое), которые появляются под соответствующим полем ввода в результате ввода некорректных данных.
        String errorMassage_all = "Поле обязательное";
        String errorMassage_1 = "Поле неправильно заполнено";
        String errorMassage_2 = "Поле заполнено некорректно";
        String errorMassage_3 = "Максимальная сумма перевода - 15 000 \u20BD";
        String errorMassage_4 = "Минимальная сумма перевода - 10 \u20BD";

        Assert.assertEquals("The error message is not correct \"Payer code for housing and communal services in Moscow\"", errorMassage_all, page.getErrorMasage(page.payerCode, page.payerCodeError, ""));
        Assert.assertEquals("The error message is not correct in field \"For what period do you pay for utilities\"", errorMassage_all, page.getErrorMasage(page.providerPeriod, page.providerPeriodError, ""));
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassage_all, page.getErrorMasage(page.amountOfPayment, page.amountOfPaymentError, ""));
        driver.navigate().refresh();

        Assert.assertEquals("The error message is not correct in field \"Payer code for housing and communal services in Moscow\"", errorMassage_1, page.getErrorMasage(page.payerCode, page.payerCodeError, "11111"));
        Assert.assertEquals("The error message is not correct in field \"For what period do you pay for utilities\"", errorMassage_2, page.getErrorMasage(page.providerPeriod, page.providerPeriodError, "132017"));
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassage_3, page.getErrorMasage(page.amountOfPayment, page.amountOfPaymentError, "15001"));
        driver.navigate().refresh();
        Assert.assertEquals("The error message is not correct in field \"Amount of payment\"", errorMassage_4, page.getErrorMasage(page.amountOfPayment, page.amountOfPaymentError, "9"));


        //8. Повторить шаг (2).
        page.ClickOnElement(page.payments);

        //9. В строке быстрого поиска поставщика услуг ввести наименование искомого (ранее сохраненного).
        page.expectation(page.input);
        WebElement elementInput = driver.findElement(page.input);
        elementInput.sendKeys(wanted);

        //10. Убедиться, что в списке предложенных провайдеров искомый поставщик первый
        page.expectation(page.dropDownList);
        WebElement firstElement = page.getFirstElement(page.dropDownList, page.internalDropDownList);
        page.expectation(page.wanted);
        Assert.assertEquals("Sought supplier is not the first", wanted, firstElement.getText());

        //11.1 Нажатием на элемент, соответствующий искомому, перейти на страницу “Оплатить ЖКУ в Москве“
        firstElement.click();

        //11.2 Убедиться, что загруженная страница та же, что и страница, загруженная в результате шага (5).
        Thread.sleep(3000);
        Assert.assertEquals("The page is not the same as the result of step 5", URL, driver.getCurrentUrl());

        //12. Выполнить шаги (2) и (3).
        page.ClickOnElement(page.payments);
        page.scrollWithOffset(page.communalPayments,0,150);
        page.ClickOnElement(page.communalPayments);

        //13. В списке регионов выбрать “г. Санкт-Петербург”.
        String SPB = "Санкт-Петербурге";
        page.changeRegion(page.regions, page.stPetersburg, SPB);
        page.expectation(page.regions);

        //14. Убедится, что в списке поставщиков на странице выбора поставщиков услуг отсутствует искомый.
        Assert.assertFalse("The required provider is present",page.verificationOfSoughtSupplier());
    }
}
