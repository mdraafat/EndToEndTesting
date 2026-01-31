package page;

import util.data.User;
import util.framework.Framework;

public class CheckoutPage {

    private final Framework framework;

    private static final String DELIVERY_ADDR_NAME = "#address_delivery .address_city.address_state_name.address_postcode";
    private static final String BILLING_ADDR_NAME = "#address_invoice .address_city.address_state_name.address_postcode";
    private static final String ADDRESS_DETAILS = "[data-qa='checkout-info']";
    private static final String REVIEW_ORDER = "#cart_info";
    private static final String COMMENT_FIELD = "textarea";
    private static final String PLACE_ORDER = ".check_out";
    private static final String NAME_ON_CARD = "[data-qa='name-on-card']";
    private static final String CARD_NUMBER = "[data-qa='card-number']";
    private static final String CVC = "[data-qa='cvc']";
    private static final String EXP_MONTH = "[data-qa='expiry-month']";
    private static final String EXP_YEAR = "[data-qa='expiry-year']";
    private static final String PAY_BUTTON = "[data-qa='pay-button']";
    private static final String DOWNLOAD_INVOICE = "[data-qa='order-placed'] ~ a";
    private static final String SUCCESS_MSG = "#success_message .alert";
    private static final String CONTINUE_BUTTON = "[data-qa='continue-button']";


    public CheckoutPage(Framework framework) {
        this.framework = framework;
    }


    public boolean sameDeliveryAddressInfo(User user) {
        return framework.getTextOf(DELIVERY_ADDR_NAME).equals(user.getCity() + " " + user.getState() + " " + user.getZipcode());
    }

    public boolean sameBillingAddressInfo(User user) {
        return framework.getTextOf(BILLING_ADDR_NAME).equals(user.getCity() + " " + user.getState() + " " + user.getZipcode());
    }

    public boolean VerifyAddressDetailsAndReviewOrder(){
        return framework.isPresent(ADDRESS_DETAILS) && framework.isPresent(REVIEW_ORDER);
    }

    public void addCommentAboutOrder(){
        framework.sendTo(COMMENT_FIELD, "Thank you Edges Academy");
    }

    public void clickPlaceOrder() {
        framework.clickOn(PLACE_ORDER);
    }

    public void enterPaymentDetails() {
        framework.removeAds();
        framework.sendTo(NAME_ON_CARD, "John Doe");
        framework.sendTo(CARD_NUMBER, "123456789");
        framework.sendTo(CVC, "123");
        framework.sendTo(EXP_MONTH, "10");
        framework.sendTo(EXP_YEAR, "2030");
    }

    public void downloadInvoice() {
        framework.clickOn(DOWNLOAD_INVOICE);
    }

    public void clickContinue() {
        framework.clickOn(CONTINUE_BUTTON);
    }

    public void clickOnPayButton() {
        framework.clickOn(PAY_BUTTON);
    }

    public String getSuccessMessage() {
        return framework.getSuccessMessage(SUCCESS_MSG);
    }

    public boolean isInvoiceDownloaded(){
        return framework.waitForFileDownload("invoice.txt", 10);
    }
}
