
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
    public void checkClickOnLinkServiceInfo() {
        String urlAfterClick = payComponent.getUrlAfterClickOnServiceInfo();
        Assert.assertEquals(
                urlAfterClick,
                "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test
    public void checkDisplayedPayedFrame() {
        WebElement payedFrame = payComponent.getPayedFrame();
        Assert.assertTrue(payedFrame.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
