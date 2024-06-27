import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PayComponent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PayComponentTest {
    WebDriver driver;
    PayComponent payComponent;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://mts.by");
        payComponent = new PayComponent(driver);
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

    @AfterClass
    public void tearDown() {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.quit();
    }
}
