import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PayComponent;

import java.util.List;


public class PayComponentTest {
    WebDriver driver;
    PayComponent payComponent;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://mts.by");
        payComponent = new PayComponent(driver);
        payComponent.rejectCookies();
    }
    @Test()
    public void checkComponentTitle() {
        String componentTitle = payComponent.getComponentTitle();
        Assert.assertEquals(componentTitle, "Онлайн пополнение без комиссии");
    }

    @Test()
    public void checkDisplayedPayPartnersLogo() {
        List<WebElement> logoPayPartnersList = payComponent.getLogoPayPartnersList();

        for(WebElement logo : logoPayPartnersList) {
            Assert.assertTrue(
                    (logo.getSize().width > 0 && logo.isDisplayed())
                    , logo.getAttribute("alt") + " не отображается на странице"
                    );
        }
    }

    @Test()
    public void clickOnLinkServiceInfo() {
        String urlAfterClick = payComponent.getUrlAfterClickOnServiceInfo();
        Assert.assertEquals(
                urlAfterClick,
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test()
    public void checkDisplayedPayedFrame() {
        WebElement payedFrame = payComponent.getPayedFrame();
        Assert.assertTrue(payedFrame.isDisplayed());
        payComponent.closePayedFrame();
    }

    // далее будет 4 очень похожих метода, не придумал как можно более универсально сделать
    // какое то количество данных в теле всё таки меняется, поэтому решил сделать так
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

    @Test()
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

    @Test()
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

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
