package pages;

import enums.PayComponentPaths;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class PayComponent {
    private WebDriver driver;

    public PayComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void rejectCookies() {
        driver.findElement(By.xpath(PayComponentPaths.COOKIES_CANCEL_BTN_LOCATOR.getPath())).click();
    }
    public String getComponentTitle() {
        return driver.findElement(
                        By.xpath(PayComponentPaths.COMPONENT_TITLE_LOCATOR.getPath()))
                .getText().replace('\n', ' ');
    }

    public List<WebElement> getLogoPayPartnersList() {
        return driver.findElement(
                        By.xpath(PayComponentPaths.PAY_PARTNERS_LOCATOR.getPath()))
                .findElements(By.xpath(PayComponentPaths.IMG_LOCATOR.getPath()));
    }

    public List<WebElement> getLogoPayPartnersListFromPayedFrame() {
        return driver.findElement(
                By.xpath(PayComponentPaths.CARDS_BRANDS_PAYED_FRAME_LOCATOR.getPath())
        ).findElements(By.xpath(PayComponentPaths.IMG_LOCATOR.getPath()));
    }

    public String getUrlAfterClickOnServiceInfo() {
        try {
            driver.findElement(By.xpath(PayComponentPaths.SERVICE_INFO_LOCATOR.getPath())).click();
            return driver.getCurrentUrl();
        } finally {
            driver.navigate().back();
        }
    }
    @Step("Переключить драйвер на активный платежный фрейм, после ожидания загрузки данных фрэйма")
    public void switchPayedFrame(String phoneNumber, String paySum) {
        driver.findElement(By.xpath(PayComponentPaths.PHONE_NUMBER_INPUT_LOCATOR.getPath())).sendKeys(phoneNumber);
        driver.findElement(By.xpath(PayComponentPaths.PAY_SUM_INPUT_LOCATOR.getPath())).sendKeys(paySum);
        driver.findElement(By.xpath(PayComponentPaths.SUBMIT_PAY_CONNECTION_BTN_LOCATOR.getPath())).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // здесь 2 ожидания для того, чтобы успели подгрузиться асинхронные компоненты
        // которые есть внутри платежного фрэйма
        // по крайней мере, без второго ожидания у меня ничего не работало
        wait.until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        By.xpath(PayComponentPaths.PAYED_FRAME_LOCATOR.getPath())
                ));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(PayComponentPaths.PAYED_FRAME_WRAPPER_LOCATOR.getPath())
                ));
    }

    @Step("Закрыть активный фрэйм")
    public void closePayedFrame() {
        driver.findElement(By.xpath(PayComponentPaths.PAYED_FRAME_CLOSE_BTN_LOCATOR.getPath())).click();
        driver.switchTo().defaultContent();
    }

    @Step("Сменить платежный сервис")
    private void changePaymentServices(String serviceTitle) {
        driver.findElement(By.xpath(PayComponentPaths.CHANGE_SERVICES_BTN_LOCATOR.getPath())).click();
        List<WebElement> selectList = driver.
                findElements(By.xpath(PayComponentPaths.PAYMENT_SERVICE_LIST_ITEM_LOCATOR.getPath()));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));

        for (WebElement select : selectList) {
            if (select.getText().equalsIgnoreCase(serviceTitle)) {
                wait.until(ExpectedConditions.elementToBeClickable(select));
                select.click();
                break;
            }
        }
    }

    @Step("Получить поля для ввода данных, у выбранного сервиса оплаты")
    private List<WebElement> getFieldsFromActivePayedForm() {
        return driver.findElements(By.xpath(
                PayComponentPaths.ACTIVE_PAYED_FORM_LOCATOR.getPath()
        ));
    }

    @Step("Получить плэйсходеры полей для ввода данных, у выбранного сервиса оплаты")
    public List<String> getFieldPlaceholdersActiveForm(String serviceTitle) {
        List<String> fieldPlaceholders = new ArrayList<>();
        changePaymentServices(serviceTitle);

        for (WebElement field : getFieldsFromActivePayedForm()) {
            fieldPlaceholders.add(field.getAttribute("placeholder"));
        }

        return fieldPlaceholders;
    }

    @Step("Получить значения полей платежного фрейма, ранее введенные в форму оплаты")
    public Map<String, String> getValueFieldsFromPayedFrame() {
        Map<String, String> payInfo = new HashMap<>();

        // получаю полностью текст с видом услуги и номером телефона, чтобы дальше извлечь оттуда только номер
        // lastIndexOf + 4 сделал для того чтобы отсеч код и оставить только цифры введённые пользователем
        String payDescription = driver.findElement(
                By.xpath(PayComponentPaths.PAY_DESCRIPTION_BLOCK_LOCATOR.getPath())
        ).getText();
        String phoneNumber = payDescription.substring(payDescription.lastIndexOf(":") + 4);

        // сохраняю сначала в переменную, чтобы по индексу найти пробел между суммой и названием валюты
        // дальше взять только сумму введённую пользователем
        String priceDescription = driver.findElement(
                By.xpath(PayComponentPaths.PRICE_DESCRIPTION_BLOCK_LOCATOR.getPath())
        ).getText();
        String price = priceDescription.substring(0, priceDescription.lastIndexOf(" "));

        // здесь то же самое - сначала получаю весь текст из кнопки, дальше беру оттуда только сумму
        String priceOnButtonDescription = driver.findElement(
                By.xpath(PayComponentPaths.BUTTON_SENDING_PAYMENT_LOCATOR.getPath())
        ).getText();
        String priceOnButton = priceOnButtonDescription.substring(
                priceOnButtonDescription.indexOf(" ") + 1, priceOnButtonDescription.lastIndexOf(" ")
        );

        payInfo.put("price", price);
        payInfo.put("phoneNumber", phoneNumber);
        payInfo.put("priceOnButton", priceOnButton);

        return payInfo;
    }

    @Step("Получить подписи полей для ввода реквизитов карты")
    public Map<String, String> getLabelFieldsFromPayedFrame() {
        Map<String, String> cardInfo = new HashMap<>();

        String cardNumberLabel = driver.findElement(
                By.xpath(PayComponentPaths.CARD_NUMBER_LABEL_LOCATOR.getPath())
        ).getText();

        String validityPeriodLabel = driver.findElement(
                By.xpath(PayComponentPaths.VALIDITY_PERIOD_LABEL_LOCATOR.getPath())
        ).getText();

        String cvcLabel = driver.findElement(
                By.xpath(PayComponentPaths.CVC_LABEL_LOCATOR.getPath())
        ).getText();

        String holderNameLabel = driver.findElement(
                By.xpath(PayComponentPaths.HOLDER_NAME_LABEL_LOCATOR.getPath())
        ).getText();

        cardInfo.put("cardNumberLabel", cardNumberLabel);
        cardInfo.put("validityPeriodLabel", validityPeriodLabel);
        cardInfo.put("cvcLabel", cvcLabel);
        cardInfo.put("holderNameLabel", holderNameLabel);

        return cardInfo;
    }

    @Step("Очистить поля формы оплаты")
    public void clearFieldsFromPayForm() {
        driver.findElement(By.xpath(PayComponentPaths.PHONE_NUMBER_INPUT_LOCATOR.getPath())).clear();
        driver.findElement(By.xpath(PayComponentPaths.PAY_SUM_INPUT_LOCATOR.getPath())).clear();
        driver.findElement(By.xpath(PayComponentPaths.EMAIL_INPUT_LOCATOR.getPath())).clear();
    }

}
