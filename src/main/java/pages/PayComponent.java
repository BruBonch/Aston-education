package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PayComponent {
    private WebDriver driver;
    private String basePathComponent = "//section[@class='pay']";
    private By componentTitleLocator = By.xpath(basePathComponent + "//h2");
    private By phoneNumberInputLocator = By.xpath(basePathComponent + "//input[@id='connection-phone']");
    private By paySumInputLocator = By.xpath(basePathComponent + "//input[@id='connection-sum']");
    private By emailInputLocator = By.xpath(basePathComponent + "//input[@id='connection-email']");
    private By payPartnersLocator = By.xpath(basePathComponent + "//div[@class='pay__partners']");
    private By serviceInfoLocator = By.xpath(basePathComponent + "//a[text() = 'Подробнее о сервисе']");

    public PayComponent(WebDriver driver) {
        this.driver = driver;
    }

    public String getComponentTitle() {
        return driver.findElement(componentTitleLocator).getText().replace('\n', ' ');
    }

    public List<WebElement> getLogoPayPartnersList() {
        return driver.findElement(payPartnersLocator).findElements(By.xpath(".//img"));
    }

}
