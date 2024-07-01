package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

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

    public List<WebElement> getLogoPayPartnersListFromPayedFrame() {
        return driver.findElement(
                By.xpath("//form[contains(@class, 'ng-tns-c61-0')]//div[contains(@class, 'cards-brands')]")
        ).findElements(By.xpath(".//img"));
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

        WebElement frame =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//iframe[@class='bepaid-iframe']"))
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'app-wrapper__content-container')]")));
        return frame;
    }

    public void switchPayedFrame(String phoneNumber, String paySum) {
        driver.findElement(phoneNumberInputLocator).sendKeys(phoneNumber);
        driver.findElement(paySumInputLocator).sendKeys(paySum);

        driver.findElement(submitPayConnectionBtn).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        By.xpath("//iframe[@class='bepaid-iframe']")));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class, 'app-wrapper__content-container')]")));
    }

    public void closePayedFrame() {
//        WebElement frame = driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']"));
//
//        driver.switchTo().frame(frame);

        // здесь по идее вызов метода уже будет означать что драйвер переключен на платежный фрэйм
        // поэтому без логики переключения, сразу закрытие и переключение на дэфольную страницу
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
    }

    ;


    public List<String> getFieldPlaceholdersActiveForm(String serviceTitle) {
        List<String> fieldPlaceholders = new ArrayList<>();
        changePaymentServices(serviceTitle);

        for (WebElement field : getFieldsFromActivePayedForm()) {
            fieldPlaceholders.add(field.getAttribute("placeholder"));
        }

        return fieldPlaceholders;
    }

    public Map<String, String> getValueFieldsFromPayedFrame() {
        Map<String, String> payInfo = new HashMap<>();

        // получить полностью текст с видом услуги и номером телефона, чтобы дальше извлечь оттуда только номер
        // lastIndexOf + 4 сделал для того чтобы отсеч код и оставить только цифры введённые пользователем
        String payDescription = driver.findElement(
//                By.xpath("//div[contains(@class, 'pay-description')]//span[@class='pay-description__text']")
                By.xpath("//div[@class = 'pay-description__text']//span")
        ).getText();
        String phoneNumber = payDescription.substring(payDescription.lastIndexOf(":") + 4);

        // сохраняю сначала в переменную, чтобы по индексу найти пробел между суммой и названием валюты
        // дальше взять только сумму введённую пользователем
        String priceDescription = driver.findElement(
                By.xpath("//div[@class='pay-description__cost']//span[contains(text(), 'BYN')]")
        ).getText();
        String price = priceDescription.substring(0, priceDescription.lastIndexOf(" "));

        // здесь то же самое - сначала получаю весь текст из кнопки, дальше беру оттуда только сумму
        String priceOnButtonDescription = driver.findElement(
                By.xpath("//div[@class='card-page__card']//button[@type='submit']")
        ).getText();
        String priceOnButton = priceOnButtonDescription.substring(
                priceOnButtonDescription.indexOf(" ") + 1, priceOnButtonDescription.lastIndexOf(" ")
        );

        payInfo.put("price", price);
        payInfo.put("phoneNumber", phoneNumber);
        payInfo.put("priceOnButton", priceOnButton);

        return payInfo;
    }

    public Map<String, String> getLabelFieldsFromPayedFrame() {
        Map<String, String> cardInfo = new HashMap<>();

        String cardNumberLabel = driver.findElement(
                By.xpath("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-1']//label")
        ).getText();

        String validityPeriodLabel = driver.findElement(
                By.xpath("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-4']//label")
        ).getText();

        String cvcLabel = driver.findElement(
                By.xpath("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-5']//label")
        ).getText();

        String holderNameLabel = driver.findElement(
                By.xpath("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-3']//label")
        ).getText();

        cardInfo.put("cardNumberLabel", cardNumberLabel);
        cardInfo.put("validityPeriodLabel", validityPeriodLabel);
        cardInfo.put("cvcLabel", cvcLabel);
        cardInfo.put("holderNameLabel", holderNameLabel);

        return cardInfo;
    }

}
