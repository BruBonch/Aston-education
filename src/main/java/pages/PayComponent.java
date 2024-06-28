package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PayComponent {
    private WebDriver driver;
    private String basePathComponent = "//section[@class='pay']";
    private By componentTitleLocator = By.xpath(basePathComponent + "//h2");
    private By phoneNumberInputLocator = By.xpath(basePathComponent + "//input[@id='connection-phone']");
    private By paySumInputLocator = By.xpath(basePathComponent + "//input[@id='connection-sum']");
    private By emailInputLocator = By.xpath(basePathComponent + "//input[@id='connection-email']");
    private By payPartnersLocator = By.xpath(basePathComponent + "//div[@class='pay__partners']");
    private By submitPayConnectionBtn = By.xpath(basePathComponent + "//form[@id='pay-connection']//button[@type='submit']");
    private By serviceInfoLocator = By.xpath(basePathComponent + "//a[text() = 'Подробнее о сервисе']");
    private By changeServicesBtn = By.xpath(basePathComponent + "//div[@class='select']");

    public PayComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void rejectCookies() {
        driver.findElement(By.xpath("//button[contains(@class, 'cookie__cancel')]")).click();
    }

    public String getComponentTitle() {
        return driver.findElement(componentTitleLocator).getText().replace('\n', ' ');
    }

    public List<WebElement> getLogoPayPartnersList() {
        return driver.findElement(payPartnersLocator).findElements(By.xpath(".//img"));
    }

    public String getUrlAfterClickOnServiceInfo() {
        try {
            driver.findElement(serviceInfoLocator).click();
            return driver.getCurrentUrl();
        } finally {
            driver.navigate().back();
        }
    }

    public WebElement getPayedFrame() {
        driver.findElement(phoneNumberInputLocator).sendKeys("297777777");
        driver.findElement(paySumInputLocator).sendKeys("10");
        driver.findElement(emailInputLocator).sendKeys("test@mail.ru");
        driver.findElement(submitPayConnectionBtn).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='bepaid-iframe']"))
        );
    }

    public void closePayedFrame() {
        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));

        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//div[@class='header__close-button']")).click();
        driver.switchTo().defaultContent();
    }

    private void changePaymentServices(String serviceTitle) {
        driver.findElement(changeServicesBtn).click();
        List<WebElement> selectList = driver.
                findElements(By.xpath(basePathComponent + "//ul//li[@class='select__item']"));

        for (WebElement select : selectList) {
            if (select.getText().equalsIgnoreCase(serviceTitle)) {
                select.click();
                break;
            }
        }
    }

    private List<WebElement> getFieldsFromActivePayedForm() {
      return driver.findElements(By.xpath(
                basePathComponent + "//form[contains(@class, 'opened')]//input"
        ));
    };


    public List<String> getFieldPlaceholdersActiveForm(String serviceTitle) {
        List<String> fieldPlaceholders = new ArrayList<>();
        changePaymentServices(serviceTitle);

        for(WebElement field : getFieldsFromActivePayedForm()) {
            fieldPlaceholders.add(field.getAttribute("placeholder"));
        }

        return fieldPlaceholders;
    }
}
