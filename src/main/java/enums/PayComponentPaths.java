package enums;

public enum PayComponentPaths {
    BASE_PATH_COMPONENT("//section[@class='pay']"),
    COMPONENT_TITLE_LOCATOR("//section[@class='pay']//h2"),
    PHONE_NUMBER_INPUT_LOCATOR("//section[@class='pay']//input[@id='connection-phone']"),
    PAY_SUM_INPUT_LOCATOR("//section[@class='pay']//input[@id='connection-sum']"),
    EMAIL_INPUT_LOCATOR("//section[@class='pay']//input[@id='connection-email']"),
    PAY_PARTNERS_LOCATOR("//section[@class='pay']//div[@class='pay__partners']"),
    SUBMIT_PAY_CONNECTION_BTN_LOCATOR("//section[@class='pay']//form[@id='pay-connection']//button[@type='submit']"),
    SERVICE_INFO_LOCATOR("//section[@class='pay']//a[text() = 'Подробнее о сервисе']"),
    CHANGE_SERVICES_BTN_LOCATOR("//section[@class='pay']//div[@class='select']"),
    COOKIES_CANCEL_BTN_LOCATOR("//button[contains(@class, 'cookie__cancel')]"),
    IMG_LOCATOR(".//img"),
    CARDS_BRANDS_PAYED_FRAME_LOCATOR("//form[contains(@class, 'ng-tns-c61-0')]//div[contains(@class, 'cards-brands')]"),
    PAYED_FRAME_LOCATOR("//iframe[@class='bepaid-iframe']"),
    PAYED_FRAME_WRAPPER_LOCATOR("//div[contains(@class, 'app-wrapper__content-container')]"),
    PAYED_FRAME_CLOSE_BTN_LOCATOR("//div[@class='header__close-button']"),
    PAYMENT_SERVICE_LIST_ITEM_LOCATOR("//section[@class='pay']//ul//li[@class='select__item']"),
    ACTIVE_PAYED_FORM_LOCATOR("//section[@class='pay']//form[contains(@class, 'opened')]//input"),
    PAY_DESCRIPTION_BLOCK_LOCATOR("//div[@class = 'pay-description__text']//span"),
    PRICE_DESCRIPTION_BLOCK_LOCATOR("//div[@class='pay-description__cost']//span[contains(text(), 'BYN')]"),
    BUTTON_SENDING_PAYMENT_LOCATOR("//div[@class='card-page__card']//button[@type='submit']"),
    CARD_NUMBER_LABEL_LOCATOR("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-1']//label"),
    VALIDITY_PERIOD_LABEL_LOCATOR("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-4']//label"),
    CVC_LABEL_LOCATOR("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-5']//label"),
    HOLDER_NAME_LABEL_LOCATOR("//form[contains(@class, 'ng-tns-c61-0')]//div[@class='content ng-tns-c46-3']//label");

    private String path;

    PayComponentPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
