import Util.Utilities;
import enums.PayComponentPaths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PayComponent;

import java.util.List;
import java.util.Map;

public class PayComponentTest {
    WebDriver driver;
    PayComponent payComponent;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://mts.by");
        driver.manage().window().fullscreen();
        payComponent = new PayComponent(driver);
        payComponent.rejectCookies();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkComponentTitle() {
        String componentTitle = payComponent.getComponentTitle();
        Assert.assertEquals(componentTitle, "Онлайн пополнение без комиссии");
    }

    @Test
    public void checkDisplayedPayPartnersLogo() {
        List<WebElement> logoPayPartnersList = payComponent.getLogoPayPartnersList();

        for(WebElement logo : logoPayPartnersList) {
            Assert.assertTrue(
                    (logo.getSize().width > 0 && logo.isDisplayed())
                    , logo.getAttribute("alt") + " не отображается на странице"
                    );
        }
    }

    @Test
    public void checkDisplayedPayPartnersLogoFromPayedFrame() {
        payComponent.switchPayedFrame("297777777", "28");

        List<WebElement> logoPayPartnersList = payComponent.getLogoPayPartnersListFromPayedFrame();

        for(WebElement logo : logoPayPartnersList) {
            Assert.assertTrue(
                    (logo.getSize().width > 0 && logo.isEnabled())
                    , logo.getAttribute("src") + " не отображается на странице"
            );
        }

        payComponent.closePayedFrame();
        payComponent.clearFieldsFromPayForm();
    }

    @Test
    public void clickOnLinkServiceInfo() {
        String urlAfterClick = payComponent.getUrlAfterClickOnServiceInfo();
        Assert.assertEquals(
                urlAfterClick,
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test
    public void checkDisplayedPayedFrame() {
        payComponent.switchPayedFrame("297777777", "20");
        Assert.assertTrue(
                driver.findElement(
                        By.xpath(PayComponentPaths.PAYED_FRAME_WRAPPER_LOCATOR.getPath())
                ).isDisplayed()
        );
        payComponent.closePayedFrame();
        payComponent.clearFieldsFromPayForm();
    }

    @Test
    public void checkPayInfo() {
        String phoneNumber = "297777777";
        String price = "20";

        payComponent.switchPayedFrame(phoneNumber, price);
        Map<String,String> result = payComponent.getValueFieldsFromPayedFrame();

        for (Map.Entry<String, String> entry : result.entrySet()) {
            switch (entry.getKey()) {
                case "phoneNumber" :
                    Assert.assertEquals(entry.getValue(), phoneNumber);
                    break;
                case "price" :
                case "priceOnButton" :
                    // создал специально класс для вспомогательных методов
                    // т.к даже при указании целой сумму, в итоге все равно получается сумма с плавающей точкой
                    // сделал отдельно метод, чтобы сравнивать введённую сумму и полученную из фрейма
                    // возможно это лишнее, просто захотелось попробовать
                    Assert.assertEquals(entry.getValue(), Utilities.getFloatingPointPaySum(price));
                    break;
            }
        }

        payComponent.closePayedFrame();
        payComponent.clearFieldsFromPayForm();
    }

    // далее будет 4 очень похожих метода, не придумал как можно более универсально сделать
    // какое то количество кода в теле всё таки меняется, поэтому решил сделать так
    @Test
    public void checkPlaceholdersConnectionPayedForm() {
        List<String> fieldsPlaceholders = payComponent.getFieldPlaceholdersActiveForm("Услуги связи");

        for(String placeholder : fieldsPlaceholders) {
            switch (placeholder) {
                case "Номер телефона":
                    Assert.assertEquals(placeholder, "Номер телефона");
                    break;
                case "Сумма":
                    Assert.assertEquals(placeholder, "Сумма");
                    break;
                case "E-mail для отправки чека":
                    Assert.assertEquals(placeholder, "E-mail для отправки чека");
                    break;
                default:
                    Assert.assertTrue(false, placeholder + " написан неверно");
            }
        }
    }

    @Test
    public void checkPlaceholdersInternetPayedForm() {
        List<String> fieldsPlaceholders = payComponent.getFieldPlaceholdersActiveForm("Домашний интернет");

        for(String placeholder : fieldsPlaceholders) {
            switch (placeholder) {
                case "Номер абонента":
                    Assert.assertEquals(placeholder, "Номер абонента");
                    break;
                case "Сумма":
                    Assert.assertEquals(placeholder, "Сумма");
                    break;
                case "E-mail для отправки чека":
                    Assert.assertEquals(placeholder, "E-mail для отправки чека");
                    break;
                default:
                    Assert.assertTrue(false, placeholder + " написан неверно");
            }
        }
    }

    @Test
    public void checkPlaceholdersInstallmentPayedForm() {
        List<String> fieldsPlaceholders = payComponent.getFieldPlaceholdersActiveForm("Рассрочка");

        for(String placeholder : fieldsPlaceholders) {
            switch (placeholder) {
                case "Номер счета на 44":
                    Assert.assertEquals(placeholder, "Номер счета на 44");
                    break;
                case "Сумма":
                    Assert.assertEquals(placeholder, "Сумма");
                    break;
                case "E-mail для отправки чека":
                    Assert.assertEquals(placeholder, "E-mail для отправки чека");
                    break;
                default:
                    Assert.assertTrue(false, placeholder + " написан неверно");
            }
        }
    }

    @Test
    public void checkPlaceholdersArrearsForm() {
        List<String> fieldsPlaceholders = payComponent.getFieldPlaceholdersActiveForm("Задолженность");

        for(String placeholder : fieldsPlaceholders) {
            switch (placeholder) {
                case "Номер счета на 2073":
                    Assert.assertEquals(placeholder, "Номер счета на 2073");
                    break;
                case "Сумма":
                    Assert.assertEquals(placeholder, "Сумма");
                    break;
                case "E-mail для отправки чека":
                    Assert.assertEquals(placeholder, "E-mail для отправки чека");
                    break;
                default:
                    Assert.assertTrue(false, placeholder + " написан неверно");
            }
        }
    }

    // group
    @Test
    public void checkLabelsCardInfo() {
        String phoneNumber = "297777777";
        String price = "20";

        payComponent.switchPayedFrame(phoneNumber, price);
        Map<String,String> result = payComponent.getLabelFieldsFromPayedFrame();

        for (Map.Entry<String, String> entry : result.entrySet()) {
            switch (entry.getKey()) {
                case "cardNumberLabel" :
                    Assert.assertEquals(entry.getValue(), "Номер карты");
                    break;
                case "validityPeriodLabel" :
                    Assert.assertEquals(entry.getValue(), "Срок действия");
                    break;
                case "cvcLabel" :
                    Assert.assertEquals(entry.getValue(), "CVC");
                    break;
                case "holderNameLabel" :
                    Assert.assertEquals(entry.getValue(), "Имя держателя (как на карте)");
                    break;
            }
        }

        payComponent.closePayedFrame();
        payComponent.clearFieldsFromPayForm();
    }
}
